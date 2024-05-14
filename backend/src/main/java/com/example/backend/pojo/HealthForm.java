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
 * @TableName health_form
 */
@TableName(value ="health_form")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthForm implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer hId;

    /**
     * 
     */
    private Integer uId;

    /**
     * 
     */
    private Double hHeight;

    /**
     * 
     */
    private String hSelColor;

    /**
     * 
     */
    private Double hSight;

    /**
     * 
     */
    private String hIsCorVision;

    /**
     * 
     */
    private String hHearing;

    /**
     * 
     */
    private String hActionBar;

    /**
     * 
     */
    private String hIsIll;

    /**
     * 
     */
    private String hHospital;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date hTime;

    /**
     * 
     */
    private String hOthIllText;

}