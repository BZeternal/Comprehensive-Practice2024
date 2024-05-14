package com.example.backend.mapper;

import com.example.backend.pojo.Reserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86199
* @description 针对表【reserve】的数据库操作Mapper
* @createDate 2024-05-14 17:44:43
* @Entity com.example.backend.pojo.Reserve
*/
@Mapper
public interface ReserveMapper extends BaseMapper<Reserve> {

}




