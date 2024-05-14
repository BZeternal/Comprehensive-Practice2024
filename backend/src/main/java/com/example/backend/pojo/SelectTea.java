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
 * @TableName select_tea
 */
@TableName(value ="select_tea")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectTea implements Serializable {
    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 教练编号
     */
    @JsonProperty("cId")
    private Integer cId;

    /**
     * 科目类型
     */
    @JsonProperty("selType")
    private String selType;

    /**
     * 选择时间
     */
    @JsonProperty("selTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date selTime;

}