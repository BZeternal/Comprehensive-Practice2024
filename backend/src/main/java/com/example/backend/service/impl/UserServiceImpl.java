package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.pojo.User;
import com.example.backend.service.UserService;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 86199
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-05-14 17:44:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> login(Map<String,String> data) {
        String uId = data.get("uId");
        String password = data.get("password");

        Map<String,String> map = new HashMap<>();

        if(uId == null || uId.equals(""))
        {
            map.put("error_info","请输入账号");
            return map;
        }

        if(password == null || password.equals("")){
            map.put("error_info","请输入密码");
            return map;
        }

        if(password.length() >= 20)
        {
            map.put("error_info","密码长度过长");
            return map;
        }

        for(int i =  0; i < uId.length(); i ++ )
        {
            if(!Character.isDigit(uId.charAt(i))){
                map.put("error_info","账号输入有误");
                return map;
            }
        }

        User user = userMapper.selectById(Integer.parseInt(uId));
        if(user == null){
            map.put("error_info","该账号不存在");
            return map;
        }

        if(!user.getPassword().equals(password)){
            map.put("error_info","账号或密码输入有误");
            return map;
        }

        map.put("error_info","success");
        map.put("uId",user.getUId().toString());
        map.put("auth",user.getAuth().toString());
        return map;
    }

    @Override
    public Map<String, String> register(Map<String,String> data) {
        String password = data.get("password");
        String rePassword = data.get("rePassword");
        Map<String,String> map=new HashMap<>();

        if(password == null || rePassword == null || password.equals("") || rePassword.equals(""))
        {
            map.put("error_info","密码不能为空");
            return map;
        }

        if(password.length() < 6 ||rePassword.length() < 6)
        {
            map.put("error_info","密码长度不能小于6");
            return map;
        }

        if(password.length() > 20 ||rePassword.length() > 20)
        {
            map.put("error_info","密码长度不能大于20");
            return map;
        }

        if(!password.equals(rePassword))
        {
            map.put("error_info","两次输入的密码不一样");
            return map;
        }

        String image = "http://47.115.203.179:8086/user.png";
        User user =new User(null,password,2,image,"非学员");
        userMapper.insert(user);

        map.put("error_info","success");
        map.put("uId",user.getUId().toString());
        return map;
    }

    @Override
    public Map<String, String> getUserInfo(Map<String,String> data) {
        String uId = data.get("uId");
        User user = userMapper.selectById(Integer.parseInt(uId));
        Map<String,String> map = new HashMap<>();
        map.put("error_info","success");
        map.put("uId",user.getUId().toString());
        map.put("auth",user.getAuth().toString());
        map.put("image", user.getImage());
        map.put("state",user.getState());
        return map;
    }
}




