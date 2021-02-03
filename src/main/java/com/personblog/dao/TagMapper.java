package com.personblog.dao;


import com.personblog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    //增
    int insertTag(Tag tag);

    //删
    void deleteTag(Long id);

    //改
    int updateTag(Tag tag);

    //查
    Tag selectTagById(Long id);

    Tag selectTagByName(String name);

    List<Tag> selectAllTag();

    //首页标签
    List<Tag> selectAllTagAndBlog();



}
