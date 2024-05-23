package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
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
     * 答题编号
     */
    @JsonProperty("prId")
    @TableId(type = IdType.AUTO)
    private Integer prId;

    /**
     * 用户编号
     */
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 答题分数
     */
    @JsonProperty("prScore")
    private Integer prScore;

    /**
     * 答题科目
     */
    @JsonProperty("prSubject")
    private Integer prSubject;

    @JsonProperty("aName")
    @TableField(exist = false)
    private String aName;

}