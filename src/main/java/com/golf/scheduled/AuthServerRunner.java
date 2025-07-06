package com.golf.scheduled;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.golf.config.KeyConfiguration;
import com.golf.util.RsaKeyHelper;

@Configuration
public class AuthServerRunner implements CommandLineRunner {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String REDIS_USER_PRI_KEY = "AG:AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "AG:AUTH:JWT:PUB";
    private static final String REDIS_SERVICE_PRI_KEY = "AG:AUTH:CLIENT:PRI";
    private static final String REDIS_SERVICE_PUB_KEY = "AG:AUTH:CLIENT:PUB";

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        try {
            if (redisTemplate.hasKey(REDIS_USER_PRI_KEY)&&redisTemplate.hasKey(REDIS_USER_PUB_KEY)&&redisTemplate.hasKey(REDIS_SERVICE_PRI_KEY)&&redisTemplate.hasKey(REDIS_SERVICE_PUB_KEY)) {
                // 尝试从 Redis 读取密钥
                keyConfiguration.setUserPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY).toString()));
                keyConfiguration.setUserPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY).toString()));
                keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PRI_KEY).toString()));
                keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PUB_KEY).toString()));
                System.out.println("Successfully loaded RSA keys from Redis");
            } else {
                generateAndStoreKeys();
            }
        } catch (Exception e) {
            System.err.println("Error loading RSA keys from Redis: " + e.getMessage());
            System.out.println("Clearing corrupted keys and generating new ones...");
            // 清理损坏的密钥
            redisTemplate.delete(REDIS_USER_PRI_KEY);
            redisTemplate.delete(REDIS_USER_PUB_KEY);
            redisTemplate.delete(REDIS_SERVICE_PRI_KEY);
            redisTemplate.delete(REDIS_SERVICE_PUB_KEY);
            // 重新生成密钥
            generateAndStoreKeys();
        }
    }
    
    private void generateAndStoreKeys() throws Exception {
        System.out.println("Generating new RSA keys...");
        Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
        keyConfiguration.setUserPriKey(keyMap.get("pri"));
        keyConfiguration.setUserPubKey(keyMap.get("pub"));
        redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
        redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
        
        keyMap = RsaKeyHelper.generateKey(keyConfiguration.getServiceSecret());
        keyConfiguration.setServicePriKey(keyMap.get("pri"));
        keyConfiguration.setServicePubKey(keyMap.get("pub"));
        redisTemplate.opsForValue().set(REDIS_SERVICE_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
        redisTemplate.opsForValue().set(REDIS_SERVICE_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
        System.out.println("New RSA keys generated and stored successfully");
    }
}
