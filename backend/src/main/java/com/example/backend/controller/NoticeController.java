package com.example.backend.controller;

import com.example.backend.mapper.NoticeMapper;
import com.example.backend.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    NoticeMapper noticeMapper;

    @PutMapping("/api/add_notice")
    public void addNotice(@RequestBody Notice notice){
        notice.setNTime(new Date());
        noticeMapper.insert(notice);
    }

    @GetMapping("/api/get_all_notice")
    public List<Notice> getAllNotice(){
        List<Notice> notices = noticeMapper.selectList(null);
        notices.sort((o1, o2) -> o2.getNTime().compareTo(o1.getNTime()));
        return notices;
    }

    @PutMapping("/api/update_notice")
    public void updateNotice(@RequestBody Notice notice){
        notice.setNTime(new Date());
        noticeMapper.updateById(notice);
    }

    @DeleteMapping("/api/delete_notice")
    public void updateNotice(@RequestParam int nId){
        noticeMapper.deleteById(nId);
    }
}
