package com.ccssoft.cloudauthenticationserver.service;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author moriarty
 * @date 2020/5/11 15:20
 */
@Component
@Slf4j
public class Ouath2UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username,"aut username can not be null");

        // (省略.......)创建UserTokenVo,它就是 要继承UserDetails接口,添加稍后要存放到token里的字段到这个类(稍后自定义实现token增强器),
        UserTokenVo user = new UserTokenVo();

        // (省略.......) (自定义去数据库根据username查找数据库用户表对应的UmsUser对象)
        UmsUser umsUser =  userService.findByUSername(username) //.....省略

        if (umsUser == null)
            return null;

        // 把umsUser的属性值复制给UserTokenVo

        // 返回  UserTokenVo
        return user;
    }
}
