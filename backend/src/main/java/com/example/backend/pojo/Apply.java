package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName apply
 */
@TableName(value ="apply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply implements Serializable {
    /**
     * 报名表编号
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("aId")
    private Integer aId;

    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 姓名
     */
    @JsonProperty("aName")
    private String aName;

    /**
     * 性别
     */
    @JsonProperty("aSex")
    private String aSex;

    /**
     * 年龄
     */
    @JsonProperty("aAge")
    private Integer aAge;

    /**
     * 电话号码
     */
    @JsonProperty("aTel")
    private String aTel;

    /**
     * 身份证
     */
    @JsonProperty("aIdCard")
    private String aIdCard;

    /**
     * 国籍
     */
    @JsonProperty("aNation")
    private String aNation;

    /**
     * 机动车类型
     */
    @JsonProperty("aCarType")
    private String aCarType;

    /**
     * 邮寄地址
     */
    @JsonProperty("aAdd")
    private String aAdd;

    /**
     * 登记日期
     */
    @JsonProperty("aTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date aTime;

    /**
     * 备注
     */
    @JsonProperty("aText")
    private String aText;

}