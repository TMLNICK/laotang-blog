package com.personblog.dao;

import com.personblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //1、根据blogId查出所有第一次评论的人（他们是没有父节点的，我这里设置为-1）
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId,
                                           @Param("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    //添加一个评论
    int saveComment(Comment comment);

    //删除评论
    void deleteComment(Long id);

    // 删除博客时调用
    List<Long> findIdsByBlogId(Long id);

}
