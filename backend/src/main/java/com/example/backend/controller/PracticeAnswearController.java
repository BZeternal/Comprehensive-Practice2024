package com.example.backend.controller;

import com.example.backend.service.PracticeAnswearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
