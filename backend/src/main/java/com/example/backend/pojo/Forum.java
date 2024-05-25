package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName forum
 */
@TableName(value ="forum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum implements Serializable {
    /**
     * 论坛id
     */
    @JsonProperty("fId")
    @TableId(type = IdType.AUTO)
    private Integer fId;

    /**
     * 内容
     */
    @JsonProperty("fContent")
    private String fContent;

    /**
     * 发表时间
     */
    @JsonProperty("fTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date fTime;

    /**
     * 题目
     */
    @JsonProperty("fTitle")
    private String fTitle;

    /**
     * 发表图片
     */
    @JsonProperty("fImg")
    private String fImg;

    /**
     * 点赞数量
     */
    @JsonProperty("fNum")
    private Integer fNum;

    /**
     * 用户id
     */
    @JsonProperty("uId")
    private Integer uId;

    @TableField(exist = false)
    List<Comment> comments;

    @TableField(exist = false)
    String image;

}