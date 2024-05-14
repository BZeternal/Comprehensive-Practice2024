package com.example.backend.mapper;

import com.example.backend.pojo.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86199
* @description 针对表【notice】的数据库操作Mapper
* @createDate 2024-05-14 17:44:38
* @Entity com.example.backend.pojo.Notice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




