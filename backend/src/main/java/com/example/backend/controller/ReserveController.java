package com.example.backend.controller;

import com.example.backend.mapper.ReserveMapper;
import com.example.backend.pojo.Reserve;
import com.example.backend.service.ReserveService;
import com.example.backend.service.utils.CarTypeNum;
import com.example.backend.service.utils.HourNum;
import com.example.backend.service.utils.SubNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReserveController {
    @Autowired
    ReserveService reserveService;

    @PostMapping("/api/reserve/user/find")
    List<Reserve> findReserveByUser(@RequestBody Map<String,String> data)
    {
        return reserveService.findReserveByUser(data);
    }

    @PostMapping("/api/reserve/insert")
    Map<String,String> insertReserve(@RequestBody Map<String,String> data)
    {
        return reserveService.insertReserve(data);
    }

    @GetMapping("/api/reserve/delete")
    Map<String,String> deleteReserve(@RequestParam Map<String,String> data)
    {
        return reserveService.deleteReserve(data);
    }

    @GetMapping("/api/reserve/getPro")
    List<Reserve> getPro(){
        return reserveService.getPro();
    }

    @GetMapping("/api/reserve/num/every")
    List<HourNum> getEveryHourNum(){
        return reserveService.getEveryHourNum();
    }

    @GetMapping("/api/reserve/num/sub")
    List<SubNum> getSubIdNum(){
        return reserveService.getSubIdNum();
    }

    @GetMapping("/api/reserve/num/type")
    List<CarTypeNum> getCarTypeNum(){
        return reserveService.getCarTypeNum();
    }

    @GetMapping("/api/reserve/num/pre")
    List<HourNum> getPreNum(){
        return reserveService.getPreNum();
    }

}
