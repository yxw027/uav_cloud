package com.ccssoft.cloudmessagemachine;


import com.ccssoft.cloudmessagemachine.redis.until.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CloudMessagemachineApplicationTests {
    @Resource
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        String str = "id:6010068390,status:A,point:[22.842657 108.333066],speed:008.00,direction:125,altitude:352,battery:100";
        String[] split = str.split(":");
        String id = split[1].split(",")[0];
        String point = split[3].split(",")[0];
        redisUtil.set(id,point);
    }



}
