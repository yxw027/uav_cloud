package com.ccssoft.cloudauth.dao;

import com.ccssoft.cloudauth.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author moriarty
 * @date 2020/5/19 18:05
 */
@Mapper
public interface UserDao {
    User getUserById (int id);

    User getUserByUsername(String username);

    int saveUserToDB(User user);

    String getSaltByUsername(String username);
}

