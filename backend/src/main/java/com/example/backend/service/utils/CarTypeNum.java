package com.example.backend.service.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarTypeNum {
    @JsonProperty("aCarType")
    String aCarType;
    Integer count;
}
