package com.personblog.controller;

import com.personblog.entity.Comment;
import com.personblog.entity.User;
import com.personblog.service.CommentService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ApiModel(value = "评论控制层")
public class CommentController{

    @Autowired
    private CommentService commentService;

    //用户评论的头像注入进来
    @Value("${comment.avatar}")
    private String avatar;

    @Value("${user.avatar}")
    private String avatar2;

    @ApiModelProperty(value = "根据博客id获取评论列表")
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    //提交
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model){
        //博客id，在前一步评论的时候注入的blogId拿到了
        Long blogId = comment.getBlogId();
        //把session中获取user,如果登录了，说明评论的博主设置博主的头像,
        // 加一个判断adminComment为true,为false说明是用户
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(avatar2);
            comment.setAdminComment(true);
        } else {
            //设置头像
            comment.setAvatar(avatar);
        }
        //如果父评论的id 不为 null（不为null说明是子节点）,那么就把这个值注入进去
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        //保存
        commentService.saveComment(comment);
        //把查出来的comment对象信息传到前端
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }
}