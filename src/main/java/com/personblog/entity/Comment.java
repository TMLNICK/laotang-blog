package com.personblog.entity;


import com.personblog.vo.DetailedBlog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "评论实体类")
public class Comment {

    @ApiModelProperty(value = "评论id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = " 邮件地址")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "父评论id")
    private Long parentCommentId;

    @ApiModelProperty(value = "是否为管理员评论")
    private boolean adminComment;


    @ApiModelProperty(value = "父评论昵称用来设置父级评论的id")
    private String parentNickname;

    @ApiModelProperty(value = "回复评论的集合")
    private List<Comment> replyComments = new ArrayList<>();

    @ApiModelProperty(value = "父评论用来显示父级评论姓名")
    private Comment parentComment;

    private DetailedBlog blog;

}
