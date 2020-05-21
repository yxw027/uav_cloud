package com.ccssoft.cloudadmin.controller;

import com.ccssoft.cloudadmin.entity.User;
import com.ccssoft.cloudadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * @author moriarty
 * @date 2020/5/20 09:50
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println(user.toString());
        //处理一下密码加密
        String salt = String.valueOf((int)(Math.random()*1000000));
        user.setSalt(salt);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userService.saveUser(user) == 1){
            return user.toString();
        } else {
            return "数据库保存失败";
        }
    }

}