package com.personblog.dao;

import com.personblog.entity.Type;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
@ApiModel(value = "分类持久层接口")
public interface TypeMapper {

    //新增分类
    int insertType(Type type);

    //删除分类
    void deleteType(Long id);

    //修改分类
    int updateType(Type type);

    //根据id查询分类
    Type selectTypeById(Long id);

    //根据分类名称来查询分类
    Type selectTypeByName(String name);

    //查询所有分类
    List<Type> selectAllType();

    //首页分类
    List<Type> selectAllTypeAndBlog();

}
