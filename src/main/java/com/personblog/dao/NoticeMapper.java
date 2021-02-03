package com.personblog.dao;

import com.personblog.entity.Notice;
import com.personblog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {
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
