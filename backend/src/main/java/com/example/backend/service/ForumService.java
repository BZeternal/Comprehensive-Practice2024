package com.example.backend.service;

import com.example.backend.pojo.Forum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【forum】的数据库操作Service
* @createDate 2024-05-21 16:06:12
*/
public interface ForumService extends IService<Forum> {
    List<Forum> getInfo();

    Map<String,String> deleteForum(Map<String,String> data);
    Map<String,String> insertForum(Map<String,String> data);

    Map<String,String> updateForum(Forum forum);
}
