package com.personblog.service;

import com.personblog.entity.Type;
import io.swagger.annotations.ApiModel;

import java.util.List;


@ApiModel(value = "分类业务层接口")
public interface TypeService {
    //增
    int insertType(Type type);

    //删
    void deleteType(Long id);

    //改
    int updateType(Type type);

    //根据id查
    Type selectTypeById(Long id);

    //根据名称查
    Type selectTypeByName(String name);

    //查询所有分类
    List<Type> selectAllType();

    //首页分类
    List<Type> selectAllTypeAndBlog();

}
