package com.golf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.golf.config.FileStorageProperties;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableScheduling
@MapperScan(basePackages="com.golf.dao.mapper")
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class App {
	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
