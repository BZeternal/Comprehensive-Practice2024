package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.mapper.ExamBmMapper;
import com.example.backend.mapper.SubTypeMapper;
import com.example.backend.pojo.ExamBm;
import com.example.backend.pojo.SubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExamController {
    @Autowired
    SubTypeMapper subTypeMapper;
    @Autowired
    ExamBmMapper examBmMapper;

    @GetMapping("/api/get_type")
    List<SubType> getType() {
        return subTypeMapper.selectList(null);
    }

    int check(int uId, int subId) {
        Map<String, Object> map = new HashMap<>();
        map.put("u_id", uId);
        map.put("sub_id", subId);
        List<ExamBm> examBmList = examBmMapper.selectByMap(map);
        if (subId > 1 && check(uId, subId - 1) != 2)
            return 3; //前置考试还未合格
        if (examBmList.size() == 0) {
            return 0; //当前考试未报名,可以报名考试
        } else {
            Integer cj = examBmList.get(0).getBmScore();
            if (cj == null) return 1; //已报名考试
            else if ((subId == 1 || subId == 4) && cj >= 90 || (subId == 2 || subId == 3) && cj >= 80)
                return 2; //考试已合格
            else
                return 0; //没报考，可以报考
        }
    }

    @GetMapping("/api/is_sign_up")
    int isSignUp(@RequestParam int uId, @RequestParam int subId) {
        return check(uId, subId);
    }

    @PutMapping("/api/can_sign_up")
    void canSignUp(@RequestBody ExamBm examBm) {
        try {
            examBmMapper.insert(examBm);
        } catch (Exception e) {
            UpdateWrapper<ExamBm> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("u_id", examBm.getUId());
            updateWrapper.eq("sub_id", examBm.getSubId());
            examBmMapper.update(examBm, updateWrapper);
        }
    }

    @GetMapping("/api/get_cj")
    List<ExamBm> getCj(@RequestParam int uId){
        Map<String,Object> map = new HashMap<>();
        map.put("u_id", uId);
        return examBmMapper.selectByMap(map);
    }

}
