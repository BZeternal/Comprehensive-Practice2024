package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.mapper.ApplyMapper;
import com.example.backend.mapper.HealthFormMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Apply;
import com.example.backend.pojo.HealthForm;
import com.example.backend.pojo.User;
import com.example.backend.service.ApplyService;
import com.example.backend.service.HealthFormService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @Autowired
    UserMapper userMapper;
    @Autowired
    ApplyMapper applyMapper;
    @Autowired
    HealthFormMapper healthFormMapper;
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

    @GetMapping("/api/user/add_user")
    void addUser(@RequestParam int uId){
        User user = new User();
        user.setUId(uId);
        user.setAuth(1);
        user.setPassword("123456");
        userMapper.insert(user);
    }
    @GetMapping("/api/user/delete_user")
    void deleteUser(@RequestParam int uId){
        userMapper.deleteById(uId);
    }
    @GetMapping("/api/user/find_user")
    List<User> findUser(@RequestParam String type,@RequestParam String key){
        Map<String,Object> map = new HashMap<>();
        map.put(type,key);
        return userMapper.selectByMap(map);
    }

    @PostMapping("/api/user/get_apply")
    List<Apply> getApply(@RequestParam int uId){
        Map<String, Object> map = new HashMap<>();
        map.put("u_id", uId);
        return applyMapper.selectByMap(map);
    }
    @PostMapping("/api/user/get_hea")
    List<HealthForm> getHea(@RequestParam int uId){
        Map<String, Object> map = new HashMap<>();
        map.put("u_id", uId);
        return healthFormMapper.selectByMap(map);
    }

    @PostMapping("/api/user/check")
    void check(@RequestParam int uId, @RequestParam String state){
        User user = userMapper.selectById(uId);
        user.setState(state);
        userMapper.updateById(user);
    }
}
