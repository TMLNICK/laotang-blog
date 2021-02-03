package com.personblog.dao;

import com.personblog.entity.User;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository // 也可以使用@Component，效果都是一样的，只是为了声明为bean(可以不加这个注解，idea虽然会提示报错，但是不影响)
@ApiModel(value = "用户持久层接口")
public interface UserMapper {

    //将参数传递给 sql,返回一个 User对象
    User login(@Param("username") String username, @Param("password") String password);
}

/*
在 Spring 程序中，Mybatis 需要找到对应的 mapper，在编译的时候动态生成代理类，
实现数据库查询功能，所以我们需要在接口上添加 @Mapper 注解。

也可以在启动类上加上 @MapperScan 扫描整个包,那么所有的这种接口都不用注解了
*/