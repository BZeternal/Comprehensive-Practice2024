package com.example.backend.service;

import com.example.backend.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 86199
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-05-21 16:06:01
*/
public interface CommentService extends IService<Comment> {
    Map<String,String> insertComment(Comment comment);
    Map<String,String> deleteComment(Map<String,String> data);
}
