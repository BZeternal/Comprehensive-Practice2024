package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.SelectTea;
import com.example.backend.service.SelectTeaService;
import com.example.backend.mapper.SelectTeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author 86199
* @description 针对表【select_tea】的数据库操作Service实现
* @createDate 2024-05-14 17:44:46
*/
@Service
public class SelectTeaServiceImpl extends ServiceImpl<SelectTeaMapper, SelectTea>
    implements SelectTeaService{
    @Autowired
    SelectTeaMapper selectTeaMapper;
    @Override
    public Map<String, String> chooseCoach(Map<String, String> data) {
        String uId = data.get("uId");
        String cId = data.get("cId");
        String selType = data.get("selType");
        Date selTime = new Date();

        SelectTea selectTea = new SelectTea(Integer.parseInt(uId),Integer.parseInt(cId),selType,selTime);
        selectTeaMapper.insert(selectTea);

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }
}




