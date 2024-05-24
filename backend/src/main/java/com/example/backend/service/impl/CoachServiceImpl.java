package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.SelectTeaMapper;
import com.example.backend.pojo.Coach;
import com.example.backend.service.CoachService;
import com.example.backend.mapper.CoachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public List<Coach> findCoach(Map<String, String> data) {
        String type = data.get("type");
        String keyword = data.get("keyword");

        if(keyword == null || keyword.equals("")){
            return coachMapper.selectList(null);
        }

        QueryWrapper<Coach> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(type,keyword);
        return  coachMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, String> insertCoach(Coach coach) {
        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");

        coachMapper.insert(coach);
        return map;
    }

    @Override
    public Map<String, String> updateCoach(Coach coach) {
        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");

        coachMapper.updateById(coach);
        return map;
    }

    @Override
    public Map<String, String> deleteCoach(Map<String,String> data) {
        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");

        coachMapper.deleteById(Integer.parseInt(data.get("cId")));
        return map;
    }
}




