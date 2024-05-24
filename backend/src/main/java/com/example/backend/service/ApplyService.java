package com.example.backend.service;

import com.example.backend.pojo.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86199
* @description 针对表【apply】的数据库操作Service
* @createDate 2024-05-14 17:44:20
*/
public interface ApplyService extends IService<Apply> {
    public void addApply(Apply apply);
}
