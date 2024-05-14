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
 * @TableName coach
 */
@TableName(value ="coach")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer cId;

    /**
     * 
     */
    private String cName;

    /**
     * 
     */
    private String cSex;

    /**
     * 
     */
    private Integer cAge;

    /**
     * 
     */
    private Integer cTeachAge;

    /**
     * 
     */
    private Integer cNum;

    /**
     * 
     */
    private String cCarType;

    /**
     * 
     */
    private String cPic;

    /**
     * 
     */
    private Double cPass;

    /**
     * 
     */
    private String cTeType;

}