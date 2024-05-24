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
 * @TableName reserve
 */
@TableName(value ="reserve")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve implements Serializable {

    @TableId(type = IdType.AUTO)
    @JsonProperty("resId")
    Integer resId;
    /**
     * 类型编号
     */
    @JsonProperty("subId")
    private Integer subId;

    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 预约开始时间
     */
    @JsonProperty("staTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date staTime;

    /**
     * 预约结束时间
     */
    @JsonProperty("enTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date enTime;

    @TableField(exist = false)
    @JsonProperty("totalHours")
    Double totalHours;

}