package com.ccssoft.cloudadmin.entity;

import com.ccssoft.cloudcommon.entity.BaseEntity;
import lombok.Data;
import lombok.NonNull;

/**
 * @author moriarty
 * @date 2020/5/19 15:30
 */
@Data
public class User extends BaseEntity {
    private static final long serialVersionUID = 4010728069921493163L;
    @NonNull
    private String username;

    private String password;

    private String name;

    private String salt;

    private String email;

    private String mobile;

    private int status;

    private int roleId;

}
