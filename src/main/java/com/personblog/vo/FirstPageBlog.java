package com.personblog.vo;


import com.personblog.entity.Type;
import com.personblog.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "博客首页列表展示vo")
public class FirstPageBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;
    private Date createTime;

    //Type
    private Type type;

    //User
    private User user;
}
