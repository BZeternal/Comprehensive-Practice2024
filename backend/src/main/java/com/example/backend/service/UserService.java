package com.example.backend.service;

import com.example.backend.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 86199
* @description 针对表【user】的数据库操作Service
* @createDate 2024-05-14 17:44:52
*/
public interface UserService extends IService<User> {
    Map<String,String> login(Map<String,String> data);
    Map<String,String> register(Map<String,String> data);

    Map<String,String> getUserInfo(Map<String,String> data);
    int updatePassword(Map<String,Object> data);
    List<User> getAllUser();
    int updateAuth(int uId, int newAuth);

    void updateSt(int uId, String str);
}
