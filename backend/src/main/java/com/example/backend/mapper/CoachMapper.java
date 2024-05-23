package com.example.backend.mapper;

import com.example.backend.pojo.Coach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【coach】的数据库操作Mapper
* @createDate 2024-05-14 17:43:45
* @Entity com.example.backend.pojo.Coach
*/
@Mapper
public interface CoachMapper extends BaseMapper<Coach> {
    List<Coach> chooseCoachList(Map<String,String> data);
}




