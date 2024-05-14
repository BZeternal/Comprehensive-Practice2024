package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Coach;
import com.example.backend.service.CoachService;
import com.example.backend.mapper.CoachMapper;
import org.springframework.stereotype.Service;

/**
* @author 86199
* @description 针对表【coach】的数据库操作Service实现
* @createDate 2024-05-14 17:43:45
*/
@Service
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach>
    implements CoachService{

}




