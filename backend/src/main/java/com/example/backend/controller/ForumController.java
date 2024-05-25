package com.example.backend.controller;

import com.example.backend.pojo.Forum;
import com.example.backend.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ForumController {
    @Autowired
    ForumService forumService;
    @GetMapping("/api/forum/info")
    List<Forum> getInfo()
    {
        return forumService.getInfo();
    }

    @PostMapping("/api/forum/insert")
    Map<String,String> insertForum(@RequestBody Map<String,String> data)
    {
        return forumService.insertForum(data);
    }

    @PostMapping("/api/forum/delete")
    Map<String,String> deleteForum(@RequestBody Map<String,String> data)
    {
        return forumService.deleteForum(data);
    }

    @PostMapping("/api/forum/update")
    Map<String,String> updateForum(@RequestBody Forum forum)
    {
        return forumService.updateForum(forum);
    }
}
