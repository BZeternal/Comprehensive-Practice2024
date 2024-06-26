package com.example.backend.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;



public class MessageUtils {
    public  static String getMessage(boolean isSys, Integer fromName, Object message){
        Map<String, Object> res = new HashMap<>();
        res.put("isSys", isSys);
        res.put("fromName", fromName);
        res.put("message", message);
        res.put("time", System.currentTimeMillis());
        return JSON.toJSONString(res);
    }
}
