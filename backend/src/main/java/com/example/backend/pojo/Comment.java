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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @JsonProperty("coId")
    @TableId(type = IdType.AUTO)
    private Integer coId;

    /**
     * 用户id
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 论坛id
     */
    @JsonProperty("fId")
    private Integer fId;

    /**
     * 评论时间
     */
    @JsonProperty("coTime")    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date coTime;

    /**
     * 评论内容
     */
    @JsonProperty("coContent")
    private String coContent;

    @TableField(exist = false)
    String image;

}