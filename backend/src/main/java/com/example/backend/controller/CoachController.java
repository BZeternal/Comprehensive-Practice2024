package com.example.backend.controller;

import com.example.backend.pojo.Coach;
import com.example.backend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/coach/find")
    List<Coach> findCoach(@RequestParam Map<String,String> data)
    {
        return coachService.findCoach(data);
    }

    @PostMapping("/api/coach/insert")
    Map<String,String> insertCoach(@RequestBody Coach coach)
    {
        return coachService.insertCoach(coach);
    }

    @PostMapping("/api/coach/update")
    Map<String,String> updateCoach(@RequestBody Coach coach)
    {
        return coachService.updateCoach(coach);
    }

    @PostMapping("/api/coach/delete")
    Map<String,String> updateCoach(@RequestBody Map<String,String> data)
    {
        return coachService.deleteCoach(data);
    }

}
