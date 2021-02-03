package com.personblog.service.impl;


import com.personblog.dao.NoticeMapper;
import com.personblog.entity.Notice;
import com.personblog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeMapper.deleteNotice(id);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public Notice selectNoticeById(Long id) {
        return noticeMapper.selectNoticeById(id);
    }
    @Override
    public List<Notice> selectAllNotice() {
        return noticeMapper.selectAllNotice();
    }

    @Override
    public Notice selectNoticeByContent(String content) {
        return noticeMapper.selectNoticeByContent(content);
    }
}
