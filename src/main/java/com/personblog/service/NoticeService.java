package com.personblog.service;

import com.personblog.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    //增
    int insertNotice(Notice notice);

    //删
    void deleteNotice(Long id);

    //改
    int updateNotice(Notice notice);

    //查
    Notice selectNoticeById(Long id);


    List<Notice> selectAllNotice();

    Notice selectNoticeByContent(String content);

}
