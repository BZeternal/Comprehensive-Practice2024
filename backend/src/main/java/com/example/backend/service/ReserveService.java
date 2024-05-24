package com.example.backend.service;

import com.example.backend.pojo.Reserve;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【reserve】的数据库操作Service
* @createDate 2024-05-14 17:44:43
*/
public interface ReserveService extends IService<Reserve> {
    List<Reserve> findReserveByUser(Map<String,String> data);
    Map<String,String> insertReserve(Map<String,String> data);

    Map<String,String> deleteReserve(Map<String,String> data);

    List<Reserve> getPro();
}
