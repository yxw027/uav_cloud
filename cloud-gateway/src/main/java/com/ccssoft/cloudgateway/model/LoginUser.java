package com.ccssoft.cloudgateway.model;

import lombok.Data;

/**
 * Created by echisan on 2018/6/23
 */
@Data
public class LoginUser {

    private String username;
    private String password;
    private Integer rememberMe;
}
