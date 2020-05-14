package com.ccssoft.cloudmessagemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Slf4j
public class CloudMessagemachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMessagemachineApplication.class, args);
    }

}
