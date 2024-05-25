package com.example.backend.mapper;

import com.example.backend.pojo.Reserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.service.utils.CarTypeNum;
import com.example.backend.service.utils.HourNum;
import com.example.backend.service.utils.SubNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【reserve】的数据库操作Mapper
* @createDate 2024-05-14 17:44:43
* @Entity com.example.backend.pojo.Reserve
*/
@Mapper
public interface ReserveMapper extends BaseMapper<Reserve> {
    List<Reserve> findReserveByUser(Map<String,String> data);
    List<Reserve> getTotalHours();

    List<HourNum> getEveryHourNum();   //获取每个时间的人数
    List<SubNum> getSubIdNum();
    List<CarTypeNum> getCarTypeNum();
    List<HourNum> getPreNum();

}




