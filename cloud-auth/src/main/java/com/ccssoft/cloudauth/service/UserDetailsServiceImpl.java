package com.ccssoft.cloudauth.service;

import com.ccssoft.cloudauth.dao.RoleDao;
import com.ccssoft.cloudauth.dao.UserDao;
import com.ccssoft.cloudauth.entity.JwtUser;
import com.ccssoft.cloudauth.entity.User;
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
