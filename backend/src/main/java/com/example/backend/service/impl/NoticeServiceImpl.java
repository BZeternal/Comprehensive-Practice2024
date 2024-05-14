package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Notice;
import com.example.backend.service.NoticeService;
import com.example.backend.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author 86199
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2024-05-14 17:44:39
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




