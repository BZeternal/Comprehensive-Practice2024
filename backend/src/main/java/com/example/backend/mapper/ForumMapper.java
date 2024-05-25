package com.example.backend.mapper;

import com.example.backend.pojo.Forum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86199
* @description 针对表【forum】的数据库操作Mapper
* @createDate 2024-05-21 16:06:12
* @Entity com.example.backend.pojo.Forum
*/
@Mapper
public interface ForumMapper extends BaseMapper<Forum> {
    List<Forum> getForumHaveImg();
}




