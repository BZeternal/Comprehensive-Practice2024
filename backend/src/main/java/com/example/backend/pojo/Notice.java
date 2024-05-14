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
 * @TableName notice
 */
@TableName(value ="notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    /**
     * 公告编号
     */
    @JsonProperty("nId")
    @TableId(type = IdType.AUTO)
    private Integer nId;

    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 标题
     */
    @JsonProperty("nTitle")
    private String nTitle;

    /**
     * 内容
     */
    @JsonProperty("nText")
    private String nText;

    /**
     * 发布时间
     */
    @JsonProperty("nTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date nTime;

}