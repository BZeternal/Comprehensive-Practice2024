package com.example.backend.mapper;

import com.example.backend.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86199
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-05-21 16:06:01
* @Entity com.example.backend.pojo.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> getCommentByfId(Integer fId);
}




