package com.example.backend.mapper;

import com.example.backend.pojo.PracticeAnswear;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.service.utils.PracticeCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【practice_answear】的数据库操作Mapper
* @createDate 2024-05-21 16:06:17
* @Entity com.example.backend.pojo.PracticeAnswear
*/
@Mapper
public interface PracticeAnswearMapper extends BaseMapper<PracticeAnswear> {
    List<PracticeAnswear> getPraInfo();
    List<PracticeAnswear> findPraInfo(Map<String,String> data);
    List<PracticeCount> getPraCouInfo();
    List<PracticeCount> findPraCouInfo(Map<String,String> data);
}




