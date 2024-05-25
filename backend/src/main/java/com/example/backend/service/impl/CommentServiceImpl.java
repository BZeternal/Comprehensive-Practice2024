package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.Comment;
import com.example.backend.service.CommentService;
import com.example.backend.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author 86199
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-05-21 16:06:01
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Autowired
    CommentMapper commentMapper;
    @Override
    public Map<String, String> insertComment(Comment comment) {
        comment.setCoTime(new Date());
        commentMapper.insert(comment);


        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }

    @Override
    public Map<String, String> deleteComment(Map<String, String> data) {
        commentMapper.deleteById(Integer.parseInt(data.get("coId")));

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }
}




