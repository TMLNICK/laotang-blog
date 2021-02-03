package com.personblog.service.impl;

import com.personblog.dao.TagMapper;
import com.personblog.entity.Tag;
import com.personblog.service.TagService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public Tag selectTagById(Long id) {
        return tagMapper.selectTagById(id);
    }

    @Override
    public Tag selectTagByName(String name) {
        return tagMapper.selectTagByName(name);
    }

    @Override
    public List<Tag> selectAllTagAndBlog() {
        return tagMapper.selectAllTagAndBlog();
    }

    @Override
    public List<Tag> selectAllTag() {
        return tagMapper.selectAllTag();
    }

    @ApiModelProperty(value = "返回tagId的集合")
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagMapper.selectTagById(aLong));
        }
        return tags;
    }

    //将带有多个标签id的字符串以逗号为分隔符取出每个id，然后添加到list集合中，返回
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        //如果ids不为空字符串或者null
        if (!"".equals(ids) && ids != null) {
            //循环添加到list中
            String[] idarray = ids.split(",");
            for (String s : idarray) {
                //用 valueOf转换为Long 对象
                list.add(Long.valueOf(s));
            }
        }
        return list;
    }
}
