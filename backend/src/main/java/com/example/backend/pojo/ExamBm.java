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
 * @TableName exam_bm
 */
@TableName(value ="exam_bm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamBm implements Serializable {
    /**
     * 
     */
    private Integer subId;

    /**
     * 
     */
    private Integer uId;

    /**
     * 
     */
    private Integer bmScore;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date bmTime;

}