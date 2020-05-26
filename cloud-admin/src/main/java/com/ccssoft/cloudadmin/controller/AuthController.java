package com.ccssoft.cloudadmin.controller;

import com.ccssoft.cloudadmin.entity.User;
import com.ccssoft.cloudadmin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author moriarty
 * @date 2020/5/20 09:50
 */
@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;
    @Value("${server.port}")
    private String port;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody User user) {
        log.info("进入AuthController.registerUser(),参数="+user.toString());
        if (user != null) {//TODO 后续挨个验证，防止前端传过来的数据使后端异常。
            //处理一下密码加密，暂时先不用了，毕竟security已自带
//        String salt = String.valueOf((int)(Math.random()*1000000));
//        user.setSalt(salt);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if(userService.saveUser(user) == 1){
                return user.toString();
            } else {
                return "数据库保存失败";
            }
        } else {
            return "注册数据不全";
        }

    }
    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword (@RequestBody User user) {
        log.info("进入AuthController.changePassword(),参数="+user.toString());
        if (user.getUsername() != null && user.getPassword() != null && userService.getUserByUsername(user.getUsername()) != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userService.updatePassword(user) == 1) {
                return "修改成功";
            }
            return "修改失败";
        } else {
            return "账号或密码为空";
        }
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public String updateInfo (@RequestBody User user) {
        log.info("进入AuthController.updateInfo(),参数="+user.toString());


    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
