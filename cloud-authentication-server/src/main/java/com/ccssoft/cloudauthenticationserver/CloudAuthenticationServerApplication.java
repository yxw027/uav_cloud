package com.ccssoft.cloudauthenticationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAuthenticationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthenticationServerApplication.class, args);
    }

}
