package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Comment;
import com.example.backend.service.CommentService;
import com.example.backend.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 86199
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-05-21 16:06:01
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




