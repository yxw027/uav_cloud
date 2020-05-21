package com.ccssoft.cloudgateway.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author moriarty
 * @date 2020/5/20 15:48
 */
@Mapper
public interface RoleDao {
    String getRoleNameById(int id);
}
