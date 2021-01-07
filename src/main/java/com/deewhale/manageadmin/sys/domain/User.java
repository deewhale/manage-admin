package com.deewhale.manageadmin.sys.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/7 下午5:18
 * @description：user
 * ******************************************/
@TableName(value = "sys_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "user_name")
    private String userName;


    private String password;
}
