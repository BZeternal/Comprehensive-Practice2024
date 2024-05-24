package com.example.backend.controller;

import com.example.backend.pojo.ExamBm;
import com.example.backend.service.ExamBmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ExamBmController {
    @Autowired
    ExamBmService examBmService;

    @GetMapping("/api/eb/judge")
    Map<String,String> judgeType(@RequestParam Map<String,String> data)
    {
        return examBmService.judgeType(data);
    }

    @GetMapping("/api/eb/info/list")
    List<ExamBm> getInfo()
    {
        return examBmService.getInfo();
    }
}
