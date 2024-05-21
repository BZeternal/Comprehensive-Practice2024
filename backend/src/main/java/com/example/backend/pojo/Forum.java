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
 * @TableName forum
 */
@TableName(value ="forum")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer fId;

    /**
     * 
     */
    private String fContent;

    /**
     * 
     */
    private Date fTime;

    /**
     * 
     */
    private String fTitle;

    /**
     * 
     */
    private String fImg;

    /**
     * 
     */
    private Integer fNum;

    /**
     * 
     */
    private Integer uId;

}