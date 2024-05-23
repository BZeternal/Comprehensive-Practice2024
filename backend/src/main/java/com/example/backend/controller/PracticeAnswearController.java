package com.example.backend.controller;

import com.example.backend.pojo.PracticeAnswear;
import com.example.backend.service.PracticeAnswearService;
import com.example.backend.service.utils.PracticeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PracticeAnswearController {
    @Autowired
    PracticeAnswearService practiceAnswearService;

    @PostMapping("/api/practice/insert")
    Map<String,String> insertScore(@RequestParam Map<String,String> data)
    {
        return practiceAnswearService.insertScore(data);
    }

    @GetMapping("/api/practice/info")
    List<PracticeAnswear> getPracticeInfo()
    {
        return practiceAnswearService.getPracticeInfo();
    }

    @GetMapping("/api/practice/count")
    List<PracticeCount> getPraCouInfo()
    {
        return practiceAnswearService.getPraCouInfo();
    }

    @GetMapping("/api/practice/find")
    List<PracticeAnswear> findPracticeInfo(@RequestParam Map<String,String> data){
        return practiceAnswearService.findPracticeInfo(data);
    }

    @GetMapping("/api/practice/find/count")
    List<PracticeCount> findPraCouInfo(@RequestParam Map<String,String> data){
        return practiceAnswearService.findPraCouInfo(data);
    }
}
