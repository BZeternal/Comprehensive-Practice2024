package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Apply;
import com.example.backend.service.ApplyService;
import com.example.backend.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【apply】的数据库操作Service实现
* @createDate 2024-05-14 17:44:20
*/
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply>
    implements ApplyService{
    @Autowired
    ApplyMapper applyMapper;
    @Override
    public void addApply(Apply apply) {
        Map<String,Object> map = new HashMap<>();
        map.put("u_id", apply.getUId());
        List<Apply> applies = applyMapper.selectByMap(map);
        if (applies != null){
            apply.setAId(applies.get(0).getAId());
            applyMapper.updateById(apply);
        }else
            applyMapper.insert(apply);

    }
}




