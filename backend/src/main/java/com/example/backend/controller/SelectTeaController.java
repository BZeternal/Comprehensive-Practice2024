package com.example.backend.controller;

import com.example.backend.service.SelectTeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SelectTeaController {
    @Autowired
    SelectTeaService selectTeaService;

    @PostMapping("/api/sel/coach")
    Map<String,String> chooseCoach(@RequestBody Map<String,String> data)
    {
        return selectTeaService.chooseCoach(data);
    }
}
