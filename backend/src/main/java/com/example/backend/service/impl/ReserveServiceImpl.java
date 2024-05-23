package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Reserve;
import com.example.backend.service.ReserveService;
import com.example.backend.mapper.ReserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【reserve】的数据库操作Service实现
* @createDate 2024-05-14 17:44:43
*/
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve>
    implements ReserveService{
    @Autowired
    ReserveMapper reserveMapper;
    @Override
    public List<Reserve> findReserveByUser(Map<String, String> data) {
        return reserveMapper.findReserveByUser(data);
    }

    @Override
    public Map<String, String> insertReserve(Map<String,String> data) {
        String uId = data.get("uId");
        String subId = data.get("subId");
        String staTime = data.get("staTime");
        String enTime = data.get("enTime");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = dateFormat.parse(staTime);
            date2 = dateFormat.parse(enTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Reserve reserve = new Reserve(null,Integer.parseInt(subId),Integer.parseInt(uId),date1,date2);
        reserveMapper.insert(reserve);

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }

    @Override
    public Map<String, String> deleteReserve(Map<String,String> data) {
        Map<String,String> map = new HashMap<>();
        int t = reserveMapper.deleteById(Integer.parseInt(data.get("resId")));
        if(t == 1)
        {
            map.put("error_info","success");
            return map;
        }
        map.put("error_info","error");
        return map;
    }
}




