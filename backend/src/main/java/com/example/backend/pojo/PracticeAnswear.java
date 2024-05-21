package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName practice_answear
 */
@TableName(value ="practice_answear")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeAnswear implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer prId;

    /**
     * 
     */
    private Integer uId;

    /**
     * 
     */
    private Integer prScore;

    /**
     * 
     */
    private Integer prSubject;

}