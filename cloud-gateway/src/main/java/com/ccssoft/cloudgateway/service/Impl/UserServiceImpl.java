package com.ccssoft.cloudgateway.service.Impl;

import com.ccssoft.cloudgateway.dao.UserDao;
import com.ccssoft.cloudgateway.entity.User;
import com.ccssoft.cloudgateway.service.UserService;
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
