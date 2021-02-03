package com.personblog.service;

import com.personblog.entity.Tag;

import java.util.List;


public interface TagService {
    //增
    int insertTag(Tag tag);

    //删
    void deleteTag(Long id);

    //改
    int updateTag(Tag tag);

    //根据id查
    Tag selectTagById(Long id);

    //根据名称查
    Tag selectTagByName(String name);

    //查询所有标签
    List<Tag> selectAllTag();

    //首页查询标签
    List<Tag> selectAllTagAndBlog();

    List<Tag> getTagByString(String text);

}
