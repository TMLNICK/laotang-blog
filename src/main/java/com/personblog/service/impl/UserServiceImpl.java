package com.personblog.service.impl;

import com.personblog.dao.UserMapper;
import com.personblog.entity.User;
import com.personblog.service.UserService;
import com.personblog.util.MD5Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ApiModel(value = "用户业务接口实现类")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @ApiModelProperty(value = "核对用户名和密码")
    @Override
    public User checkUser(String username, String password) {
        return userMapper.login(username, MD5Utils.code(password));
    }
}
