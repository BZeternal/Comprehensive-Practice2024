package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Forum;
import com.example.backend.service.ForumService;
import com.example.backend.mapper.ForumMapper;
import org.springframework.stereotype.Service;

/**
* @author 86199
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2024-05-21 16:06:12
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{

}




