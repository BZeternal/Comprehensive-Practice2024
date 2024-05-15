package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.HealthForm;
import com.example.backend.service.HealthFormService;
import com.example.backend.mapper.HealthFormMapper;
import org.springframework.stereotype.Service;

/**
* @author 86199
* @description 针对表【health_form】的数据库操作Service实现
* @createDate 2024-05-14 17:44:31
*/
@Service
public class HealthFormServiceImpl extends ServiceImpl<HealthFormMapper, HealthForm>
    implements HealthFormService{

}




