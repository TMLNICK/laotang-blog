package com.personblog.vo;

import com.personblog.entity.Tag;
import com.personblog.entity.Type;
import com.personblog.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "博客详情页展示vo")
public class DetailedBlog {

    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private Date createTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;

    private List<Tag> tags = new ArrayList<>();
    private Type type;
    private User user;
}
