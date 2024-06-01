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
 * @TableName sub_type
 */
@TableName(value ="sub_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubType implements Serializable {
    /**
     * 类型编号
     */
    @JsonProperty("subId")
    @TableId(type = IdType.AUTO)
    private Integer subId;

    /**
     * 科目名
     */
    @JsonProperty("subName")
    private String subName;

    @JsonProperty("img")
    private String img;
}