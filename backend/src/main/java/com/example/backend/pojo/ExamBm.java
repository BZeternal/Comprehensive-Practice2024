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
 * @TableName exam_bm
 */
@TableName(value ="exam_bm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamBm implements Serializable {
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
     * 成绩
     */
    @JsonProperty("bmScore")
    private Integer bmScore;

    /**
     * 报名时间
     */
    @JsonProperty("bmTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date bmTime;

}