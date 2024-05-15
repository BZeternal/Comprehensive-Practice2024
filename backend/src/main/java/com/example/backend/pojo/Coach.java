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
 * @TableName coach
 */
@TableName(value ="coach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach implements Serializable {
    /**
     * 教练编号
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("cId")
    private Integer cId;

    /**
     * 教练姓名
     */
    @JsonProperty("cName")
    private String cName;

    /**
     * 教练性别
     */
    @JsonProperty("cSex")
    private String cSex;

    /**
     * 教练年龄
     */
    @JsonProperty("cAge")
    private Integer cAge;

    /**
     * 教练教龄
     */
    @JsonProperty("cTeachAge")
    private Integer cTeachAge;

    /**
     * 教练学员数量
     */
    @JsonProperty("cNum")
    private Integer cNum;

    /**
     * 教练类型
     */
    @JsonProperty("cCarType")
    private String cCarType;

    /**
     * 教练照片
     */
    @JsonProperty("cPic")
    private String cPic;

    /**
     * 及格率
     */
    @JsonProperty("cPass")
    private Double cPass;

    /**
     * 教学科目
     */
    @JsonProperty("cTeType")
    private String cTeType;

}