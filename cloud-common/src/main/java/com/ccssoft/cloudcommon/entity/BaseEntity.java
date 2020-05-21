package com.ccssoft.cloudcommon.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author moriarty
 * @date 2020/5/19 15:32
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -6507594581709611025L;
    public int id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//通过json输出到前端的时候会格式化
    public Date updateTime = new Date();
}
