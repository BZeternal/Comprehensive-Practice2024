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
 * @TableName sub_type
 */
@TableName(value ="sub_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubType implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer subId;

    /**
     * 
     */
    private String subName;


}