package com.example.backend.service;

import com.example.backend.pojo.PracticeAnswear;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 86199
* @description 针对表【practice_answear】的数据库操作Service
* @createDate 2024-05-21 16:06:17
*/
public interface PracticeAnswearService extends IService<PracticeAnswear> {
    Map<String,String> insertScore(Map<String,String> data);
}
