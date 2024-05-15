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
 * @TableName health_form
 */
@TableName(value ="health_form")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthForm implements Serializable {
    /**
     * 体检表编号
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("hId")
    private Integer hId;

    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 身高
     */
    @JsonProperty("hHeight")
    private Double hHeight;

    /**
     * 辨色力
     */
    @JsonProperty("hSelColor")
    private String hSelColor;

    /**
     * 视力
     */
    @JsonProperty("hSight")
    private Double hSight;

    /**
     * 视力是否矫正
     */
    @JsonProperty("hIsCorVision")
    private String hIsCorVision;

    /**
     * 听力
     */
    @JsonProperty("hHearing")
    private String hHearing;

    /**
     * 躯干是否有运动障碍
     */
    @JsonProperty("hActionBar")
    private String hActionBar;

    /**
     * 是否还有其他疾病
     */
    @JsonProperty("hIsIll")
    private String hIsIll;

    /**
     * 体检医院
     */
    @JsonProperty("hHospital")
    private String hHospital;

    /**
     * 体检日期
     */
    @JsonProperty("hTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date hTime;

    /**
     * 其他病例备注
     */
    @JsonProperty("hOthIllText")
    private String hOthIllText;

}