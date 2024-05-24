package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.pojo.Apply;
import com.example.backend.pojo.HealthForm;
import com.example.backend.pojo.User;
import com.example.backend.service.ApplyService;
import com.example.backend.service.HealthFormService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ApplyService applyService;
    @Autowired
    HealthFormService healthFormService;
    @PostMapping("/api/login")
    Map<String,String> login(@RequestParam Map<String,String> data)
    {
        return userService.login(data);
    }

    @PostMapping("/api/register")
    Map<String,String> register(@RequestParam Map<String,String> data){
        return userService.register(data);
    }

    @PostMapping("/api/user/info")
    Map<String,String> getUserInfo(@RequestParam Map<String,String> data)
    {
        return userService.getUserInfo(data);
    }

    @PostMapping("/api/user/update_password")
    int updatePassword(@RequestBody Map<String,Object> data){
        return userService.updatePassword(data);
    }

    @PostMapping("/api/user/getAll")
    List<User> getAllures(){
        return userService.getAllUser();
    }

    @PostMapping("/api/user/update_auth")
    int updateAuth(@RequestParam int uId,@RequestParam int newAuth){
        return userService.updateAuth(uId, newAuth);
    }

    @PostMapping("/api/user/addUserInfo")
    int addUserInfo(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        Apply app = jsonObject.getObject("apply",Apply.class);
        HealthForm healthForm = jsonObject.getObject("healthForm",HealthForm.class);
        applyService.addApply(app);
        healthFormService.addHealth(healthForm);
        userService.updateSt(app.getUId(),"审核中");
        return 1;
    }
}
