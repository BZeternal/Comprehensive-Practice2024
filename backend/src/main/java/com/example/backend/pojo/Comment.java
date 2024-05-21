package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer coId;

    /**
     * 
     */
    private Integer uId;

    /**
     * 
     */
    private Integer fId;

    /**
     * 
     */
    private Date coTime;

    /**
     * 
     */
    private String coContent;

}