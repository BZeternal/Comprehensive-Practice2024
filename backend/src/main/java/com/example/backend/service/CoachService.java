package com.example.backend.service;

import com.example.backend.pojo.Coach;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【coach】的数据库操作Service
* @createDate 2024-05-14 17:43:45
*/
public interface CoachService extends IService<Coach> {

    List<Coach> getCoachInfo();   //获取所有教练信息
    Coach selectCoach(Map<String,String> data);  //根据uId和科目获取教练信息
    List<Coach> chooseCoachList(Map<String,String> data); //根据驾驶类型和科目获取教练信息列表
    List<Coach> findCoach(Map<String,String> data);
    Map<String,String> insertCoach(Coach coach);
    Map<String,String> updateCoach(Coach coach);

    Map<String,String> deleteCoach(Map<String,String> data);
}
