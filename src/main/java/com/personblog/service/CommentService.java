package com.personblog.service;

import com.personblog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    //根据博客id获取评论列表
    List<Comment> listCommentByBlogId(Long blogId);

    //保存评论
    int saveComment(Comment comment);

}
