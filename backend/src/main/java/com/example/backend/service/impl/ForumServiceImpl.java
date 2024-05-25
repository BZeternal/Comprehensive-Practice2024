package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.pojo.Comment;
import com.example.backend.pojo.Forum;
import com.example.backend.service.ForumService;
import com.example.backend.mapper.ForumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2024-05-21 16:06:12
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{
    @Autowired
    ForumMapper forumMapper;
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Forum> getInfo() {
        List<Forum> forums = forumMapper.getForumHaveImg();

        for(Forum forum : forums)
        {
            forum.setComments(commentMapper.getCommentByfId(forum.getFId()));
        }
        return forums;
    }

    @Override
    public Map<String, String> deleteForum(Map<String, String> data) {
        forumMapper.deleteById(data.get("fId"));

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }

    @Override
    public Map<String, String> insertForum(Map<String, String> data) {
        String fContent = data.get("fContent");
        String fTitle = data.get("fTitle");
        String uId = data.get("uId");
        String fImg = data.get("fImg");

        Forum forum = new Forum(null,fContent,new Date(),fTitle,fImg,
                0,Integer.parseInt(uId),null,null);
        forumMapper.insert(forum);

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }

    @Override
    public Map<String, String> updateForum(Forum forum) {
        forumMapper.updateById(forum);

        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        return map;
    }
}




