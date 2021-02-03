package com.personblog.service.impl;

import com.personblog.dao.TypeMapper;
import com.personblog.entity.Type;
import com.personblog.service.TypeService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@ApiModel(value = "分类业务接口实现类")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public int insertType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public Type selectTypeById(Long id) {
        return typeMapper.selectTypeById(id);
    }

    @Override
    public Type selectTypeByName(String name) {
        return typeMapper.selectTypeByName(name);
    }

    @Override
    public List<Type> selectAllType() {
        return typeMapper.selectAllType();
    }

    @Override
    public List<Type> selectAllTypeAndBlog() {
        return typeMapper.selectAllTypeAndBlog();
    }
}
