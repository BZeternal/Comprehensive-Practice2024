package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.PracticeAnswear;
import com.example.backend.service.PracticeAnswearService;
import com.example.backend.mapper.PracticeAnswearMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 86199
* @description 针对表【practice_answear】的数据库操作Service实现
* @createDate 2024-05-21 16:06:17
*/
@Service
public class PracticeAnswearServiceImpl extends ServiceImpl<PracticeAnswearMapper, PracticeAnswear>
    implements PracticeAnswearService{
    @Autowired
    PracticeAnswearMapper practiceAnswearMapper;
    @Override
    public Map<String, String> insertScore(Map<String, String> data) {
        String uId = data.get("uId");
        String prScore = data.get("prScore");
        String prSubject = data.get("prSubject");

        Map<String,String> map = new HashMap<>();

        PracticeAnswear practiceAnswear = new PracticeAnswear(null,Integer.parseInt(uId),
                Integer.parseInt(prScore),Integer.parseInt(prSubject));
        practiceAnswearMapper.insert(practiceAnswear);

        map.put("error_info","success");
        return map;
    }
}




