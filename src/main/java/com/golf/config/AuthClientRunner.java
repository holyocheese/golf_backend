package com.golf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.golf.common.BaseResponse;
import com.golf.common.ServiceAuthConfig;
import com.golf.service.AuthClientService;

import groovy.util.logging.Slf4j;

@Configuration
public class AuthClientRunner implements CommandLineRunner {
	private static final Logger log= LoggerFactory.getLogger(AuthClientRunner.class);

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化加载用户pubKey");
        try {
       //     refreshUserPubKey();
        }catch(Exception e){
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!",e);
        }
        log.info("初始化加载客户pubKey");
        try {
        //    refreshServicePubKey();
        }catch(Exception e){
            log.error("初始化加载客户pubKey失败,1分钟后自动重试!",e);
        }
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey() throws Exception{
    	authClientService.validate(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
    	this.userAuthConfig.setPubKeyByte(keyConfiguration.getUserPubKey()); 
    	log.info("userAuthConfig："+this.userAuthConfig.getPubKeyByte().toString());
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshServicePubKey() throws Exception{
    	authClientService.validate(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        this.serviceAuthConfig.setPubKeyByte(keyConfiguration.getServicePubKey());
        log.info("userAuthConfig："+this.serviceAuthConfig.getPubKeyByte().toString());
    }

}