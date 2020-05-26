package com.ccssoft.cloudauth.controller;

import com.ccssoft.cloudauth.entity.User;
import com.ccssoft.cloudauth.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author moriarty
 * @date 2020/5/26 14:31
 */
@RestController
public class AuthController {
    @Resource
    private AdminService adminService;

    @PostMapping(value = "/consumer/admin/register")
    public String registerUser(@RequestBody User user) {
        return adminService.registerUser(user);
    }
}
