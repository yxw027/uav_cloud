package com.ccssoft.cloudadmin.service.impl;

import com.ccssoft.cloudadmin.dao.UserDao;
import com.ccssoft.cloudadmin.entity.User;
import com.ccssoft.cloudadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author moriarty
 * @date 2020/5/20 10:04
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public int saveUser(User user) {
        return userDao.saveUserToDB(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public String getSaltByUsername(String username) {
        return userDao.getSaltByUsername(username);
    }
}
