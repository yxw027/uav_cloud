package com.ccssoft.cloudadmin;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudAdminApplicationTests {

    @Test
    void contextLoads() {
        new Account(null);

    }


}
@Data
class UserTest {
    private String name = "moriarty";
}
class Account {
    UserTest userTest;

    @Autowired
    public Account (UserTest userTest) {
        this.userTest = userTest;
        System.out.println(this.userTest.getName());
    }

}
