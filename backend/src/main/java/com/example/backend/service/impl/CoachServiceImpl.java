package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.SelectTeaMapper;
import com.example.backend.pojo.Coach;
import com.example.backend.service.CoachService;
import com.example.backend.mapper.CoachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【coach】的数据库操作Service实现
* @createDate 2024-05-14 17:43:45
*/
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach>
    implements CoachService{
    @Autowired
    CoachMapper coachMapper;
    @Autowired
    SelectTeaMapper selectTeaMapper;
    @Override
    public List<Coach> getCoachInfo() {
        return coachMapper.selectList(null);
    }

    @Override
    public Coach selectCoach(Map<String, String> data) {
        return selectTeaMapper.selectCoach(data);
    }

    @Override
    public List<Coach> chooseCoachList(Map<String, String> data) {
        String t = data.get("cTeType");
        System.out.println(data);
        if(t.equals("2")) t = "科二";
        else t = "科三";
        data.put("cTeType",t);
        return coachMapper.chooseCoachList(data);
    }
}




