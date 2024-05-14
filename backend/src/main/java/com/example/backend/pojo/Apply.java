package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer aId;

    /**
     * 
     */
    private Integer uId;

    /**
     * 
     */
    private String aName;

    /**
     * 
     */
    private String aSex;

    /**
     * 
     */
    private Integer aAge;

    /**
     * 
     */
    private String aTel;

    /**
     * 
     */
    private String aIdCard;

    /**
     * 
     */
    private String aNation;

    /**
     * 
     */
    private String aCarType;

    /**
     * 
     */
    private String aAdd;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date aTime;

    /**
     * 
     */
    private String aText;

}