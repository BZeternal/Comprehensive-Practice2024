package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.ExamBm;
import com.example.backend.service.ExamBmService;
import com.example.backend.mapper.ExamBmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【exam_bm】的数据库操作Service实现
* @createDate 2024-05-21 16:02:27
*/
@Service
public class ExamBmServiceImpl extends ServiceImpl<ExamBmMapper, ExamBm>
    implements ExamBmService{
    @Autowired
    ExamBmMapper examBmMapper;
    @Override
    public Map<String, String> judgeType(Map<String, String> data) {
        String uId = data.get("uId");
        Map<String,String> map = new HashMap<>();

        QueryWrapper<ExamBm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",uId);
        queryWrapper.eq("sub_id",2);
        queryWrapper.ge("bm_score",80);//是否考完科目2

        List<ExamBm> list;
        list = examBmMapper.selectList(queryWrapper);
        if(list.size() > 0)
        {
            map.put("error_info","success");  //科目2已考完
            map.put("subId", "3");
            return map;
        }

        queryWrapper.clear();
        queryWrapper.eq("u_id",uId);
        queryWrapper.eq("sub_id",1);
        queryWrapper.ge("bm_score",90);

        list = examBmMapper.selectList(queryWrapper);
        if(list.size() > 0)
        {
            map.put("error_info","success");  //科目1已考完
            map.put("subId", "2");
            return map;
        }

        map.put("error_info","error");
        map.put("subId", null);
        return map;
    }
}




