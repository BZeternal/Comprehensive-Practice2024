package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.HealthForm;
import com.example.backend.service.HealthFormService;
import com.example.backend.mapper.HealthFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【health_form】的数据库操作Service实现
* @createDate 2024-05-14 17:44:31
*/
@Service
public class HealthFormServiceImpl extends ServiceImpl<HealthFormMapper, HealthForm>
    implements HealthFormService{
    @Autowired
    HealthFormMapper healthFormMapper;


    @Override
    public void addHealth(HealthForm healthForm) {
        Map<String,Object> map = new HashMap<>();
        map.put("u_id",healthForm.getUId());
        List<HealthForm> healthForms = healthFormMapper.selectByMap(map);
        if (healthForms != null) {
            healthForm.setHId(healthForms.get(0).getHId());
            healthFormMapper.updateById(healthForm);
        }else
            healthFormMapper.insert(healthForm);

    }
}




