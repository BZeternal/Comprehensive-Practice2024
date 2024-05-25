package com.example.backend.controller;

import com.example.backend.pojo.ExamBm;
import com.example.backend.service.ExamBmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/eb/update")
    Map<String,String> updateScore(@RequestBody Map<String,String> data)
    {
        return examBmService.updateScore(data);
    }

    @GetMapping("/api/eb/find")
    List<ExamBm> findInfo(@RequestParam Map<String,String> data)
    {
        return examBmService.findInfo(data);
    }
}
