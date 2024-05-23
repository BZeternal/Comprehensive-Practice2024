package com.example.backend.mapper;

import com.example.backend.pojo.Coach;
import com.example.backend.pojo.SelectTea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* @author 86199
* @description 针对表【select_tea】的数据库操作Mapper
* @createDate 2024-05-14 17:44:46
* @Entity com.example.backend.pojo.SelectTea
*/
@Mapper
public interface SelectTeaMapper extends BaseMapper<SelectTea> {

    Coach selectCoach(Map<String,String> data);
}




