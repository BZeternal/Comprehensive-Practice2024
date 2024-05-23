package com.example.backend.mapper;

import com.example.backend.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86199
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-05-14 17:44:52
* @Entity com.example.backend.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByuId (Integer uId);

}




