package com.example.backend.controller;

import com.example.backend.pojo.Forum;
import com.example.backend.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    @PostMapping("/api/forum/upload")
    public Map<String,String> upload(@RequestParam(value = "file",required = false)MultipartFile file) throws IOException {
        Map<String,String> map = new HashMap<>();
        if(file.isEmpty())
        {
            map.put("error_info","error");
            return map;
        }

        String name = file.getOriginalFilename();
        name = System.currentTimeMillis() + "." + name.substring(name.lastIndexOf(".")+1);
        String path = "/home/serve/backend/file/";
        File dest = new File(path + name);
        file.transferTo(dest);
        map.put("error_info","success");
        map.put("path","http://47.115.203.179:8086/" + name);
        return map;
    }
}
