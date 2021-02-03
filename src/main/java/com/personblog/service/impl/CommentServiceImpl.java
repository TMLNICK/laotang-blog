package com.personblog.service.impl;


import com.personblog.dao.BlogMapper;
import com.personblog.dao.CommentMapper;
import com.personblog.entity.Comment;
import com.personblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    //所有子评论都放到list中，返回Comment对象
    private List<Comment> tempReplys = new ArrayList<>();

    //根据博客id获取评论列表
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //1、根据blogId查出所有第一次评论的人（他们是没有父节点的，我这里设置为-1）
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        //2、对这些父评论进行遍历
        for(Comment comment : comments){
            //3、得到他们的 id和 昵称
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();

            //4、根据上面得到的父评论id查到他们的子评论-
            List<Comment> childComments = commentMapper.findByBlogIdParentIdNotNull(blogId,id);
            //5、调用方法查询出该父评论的对应的所有的子评论（如果有的话）----根据blogId,子评论的list集合，父评论的昵称
            combineChildren(blogId, childComments, parentNickname1);
            //6、得到所有子评论的对象
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        //5.1 判断是否有一级子评论
        if(childComments.size() > 0){
            //5.2 如果有的话，直接遍历该list集合
            for(Comment childComment : childComments){
                //这个时候就可以获取到该子评论的人的昵称并设置进去
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);

                //然后 new一个list,把所有的子评论又放到一个list中
                tempReplys.add(childComment);
                //得到所有一级子评论的id
                Long childId = childComment.getId();

                //5.2.1 查询出子二级评论 根据blogId,子评论id,和这个昵称
                //再写一个方法，逻辑和上面一样
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    //和上面方法类似，找到回复子评论的评论，并且add到集合中
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBlogIdAndReplayId(blogId,childId);
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentMapper.saveComment(comment);
        //文章评论计数
        blogMapper.getCommentCountById(comment.getBlogId());
        return comments;
    }

}
