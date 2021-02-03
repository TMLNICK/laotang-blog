package com.personblog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "博客实体类")
public class Blog {

    @ApiModelProperty(value = "博客id")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "首图")
    private String firstPicture;

    @ApiModelProperty(value = "标记")
    private String flag;

    @ApiModelProperty(value = "浏览次数")
    private Integer views;

    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否开启赞赏")
    private boolean appreciation;

    @ApiModelProperty(value = "是否开启版权")
    private boolean shareStatement;

    @ApiModelProperty(value = "是否开启评论")
    private boolean commentabled;

    @ApiModelProperty(value = "是否发布")
    private boolean published;

    @ApiModelProperty(value = "是否推荐")
    private boolean recommend;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "分类id")
    private Long typeId;

    @ApiModelProperty(value = "获取多个标签的id")
    private String tagIds;

    //关联属性
    @ApiModelProperty(value = "分类对象")
    private Type type;

    @ApiModelProperty(value = "用户对象")
    private User user;

    @ApiModelProperty(value = "评论集合")
    private List<Comment> comments = new ArrayList<>();

    @ApiModelProperty(value = "标签集合")
    private List<Tag> tags = new ArrayList<>();


}
