package com.example.backend.controller;

import com.example.backend.pojo.Coach;
import com.example.backend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CoachController {
    @Autowired
    CoachService coachService;

    @GetMapping("/api/coach/info")
    List<Coach> getCoachInfo()
    {
        return coachService.getCoachInfo();
    }

    @GetMapping("/api/coach/select/user")
    Coach selectCoach(@RequestParam Map<String,String> data)
    {
        return coachService.selectCoach(data);
    }

    @GetMapping("/api/coach/choose")
    List<Coach> chooseCoachList(@RequestParam Map<String,String> data)
    {
        return coachService.chooseCoachList(data);
    }

}
