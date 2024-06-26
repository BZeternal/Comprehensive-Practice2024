package com.example.backend.service;

import com.example.backend.pojo.ExamBm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【exam_bm】的数据库操作Service
* @createDate 2024-05-21 16:02:27
*/
public interface ExamBmService extends IService<ExamBm> {
    Map<String,String> judgeType(Map<String,String> data);
    List<ExamBm> getInfo(); //获取所有成绩信息
    Map<String,String> updateScore(Map<String,String> data);

    List<ExamBm> findInfo(Map<String,String> data); //模糊查询
}
