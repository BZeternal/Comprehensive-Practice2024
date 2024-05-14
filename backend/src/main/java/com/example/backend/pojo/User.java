package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 用户编号
     */
    @JsonProperty("uId")
    @TableId(type = IdType.AUTO)
    private Integer uId;

    /**
     * 密码
     */
    @JsonProperty("password")
    private String password;

    /**
     * 权限
     */
    @JsonProperty("auth")
    private Integer auth;

    /**
     * 头像
     */
    @JsonProperty("image")
    private String image;

    /**
     * 状态
     */
    @JsonProperty("state")
    private String state;
}