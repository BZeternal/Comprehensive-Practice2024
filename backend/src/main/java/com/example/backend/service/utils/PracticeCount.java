package com.example.backend.service.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeCount {
    @JsonProperty("uId")
    private Integer uId;

    /**
     * 答题科目
     */
    @JsonProperty("prSubject")
    private Integer prSubject;
    @JsonProperty("avgValue")
    Double avgValue;
    @JsonProperty("maxValue")
    Integer maxValue;
    @JsonProperty("quaNum")
    Integer quaNum;
    @JsonProperty("aName")
    private String aName;
    Integer count;
}
