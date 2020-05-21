package com.ccssoft.cloudgateway.service.Impl;

import com.ccssoft.cloudgateway.dao.RoleDao;
import com.ccssoft.cloudgateway.dao.UserDao;
import com.ccssoft.cloudgateway.entity.JwtUser;
import com.ccssoft.cloudgateway.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by echisan on 2018/6/23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(s);
        return new JwtUser(user,roleDao);
    }

}
