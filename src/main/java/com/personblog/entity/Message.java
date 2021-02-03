package com.personblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "留言实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @ApiModelProperty(value = "留言id")
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

    @ApiModelProperty(value = "父评论id")
    private Long parentMessageId;

    @ApiModelProperty(value = "是否为管理员评论")
    private boolean adminMessage;

    @ApiModelProperty(value = "回复评论的集合")
    private List<Message> replyMessages = new ArrayList<>();

    @ApiModelProperty(value = "父评论用来显示父级评论姓名")
    private Message parentMessage;

    @ApiModelProperty(value = "父评论昵称用来设置父级评论的id")
    private String parentNickname;


}