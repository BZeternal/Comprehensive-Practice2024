package com.example.backend.mapper;

import com.example.backend.pojo.ExamBm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【exam_bm】的数据库操作Mapper
* @createDate 2024-05-21 16:02:27
* @Entity com.example.backend.pojo.ExamBm
*/
@Mapper
public interface ExamBmMapper extends BaseMapper<ExamBm> {
    List<ExamBm> findPro();
    List<ExamBm> getInfo();
    List<ExamBm> findInfo(Map<String,String> data);
    void updateScore(Map<String,String> data);
}




