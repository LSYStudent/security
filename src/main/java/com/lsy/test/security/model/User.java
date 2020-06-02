package com.lsy.test.security.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lsy.test.security.config.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户类
 * @author lsy
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user")
public class User extends Model<User> {


    private static final long serialVersionUID = -2968923187927141515L;

    public User defv() {
        setType(Constants.NORMAL);
        setStatus(Constants.NORMAL);
        setResult(Constants.NORMAL);
        setCreateTime(LocalDateTime.now());
        setUpdateTime(getCreateTime());
        setRealNameAuth(Constants.ILLEGAL);
        return this;
    }


    @TableId(value = "id", type =  IdType.AUTO)
    private Long id;

    @TableField(value = "type")
    private Integer type;

    @TableLogic
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "result")
    private Integer result;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "bank_card")
    private String bankCard;

    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /**
     * 头像
     */
    @TableField(value = "avator_img")
    private String avatorImg;

    /**
     * 个性签名
     */
    @TableField(value = "user_signature")
    private String userSignature;

    /**
     * 用户是否认证 实名认证  0 否     1 是
     */
    @TableField(value = "real_name_auth")
    private Integer realNameAuth;

    @TableField(value = "reg_ip")
    private String regIp;

    @TableField(value = "login_ip")
    private String loginIp;

    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    @TableField(value = "out_time")
    private LocalDateTime outTime;


}
