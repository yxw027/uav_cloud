package com.ccssoft.cloudadmin.dao;

import com.ccssoft.cloudadmin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);
}

