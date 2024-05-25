package com.example.backend.controller;

import com.example.backend.pojo.Comment;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/api/comment/insert")
    Map<String,String> insertComment(@RequestBody Comment comment)
    {
        return commentService.insertComment(comment);
    }

    @PostMapping("/api/comment/delete")
    Map<String,String> deleteComment(@RequestBody Map<String,String> data)
    {
        return commentService.deleteComment(data);
    }
}
