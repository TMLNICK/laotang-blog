package com.personblog.dao;

import com.personblog.entity.Blog;
import com.personblog.entity.Count;
import com.personblog.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface BlogMapper {

    //1、查询文章列表
    List<BlogQuery> getAllBlogQuery();

    //2、新增文章
    int saveBlog(Blog blog);
    //把标签保存在中间表中
    int saveBlogAndTag(BlogAndTag blogAndTag);

    //3、根据title和type搜索对应博客列表
    List<BlogQuery> searchByTitleOrType(SearchBlog searchBlog);

    //4、删除博客
    int deleteBlogAndTag(Long blogId);
    int deleteBlog(Long id);

    //5、修改修改博客
    int updateBlog(Blog blog);
    //根据id查询博客信息，在修改页面做一个回显
    Blog getBlogById(Long id);


    //博客首页
    List<FirstPageBlog> getFirstPageBlog();

    //推荐
    List<RecommendBlog> getAllRecommendBlog();

    //全局搜索
    List<FirstPageBlog> getSearchBlog(String query);

    //博客详情
    DetailedBlog getDetailedBlog(Long id);

    //更新观看数
    int updateViews(Long id);

    //查询评论数
    int getCommentCountById(Long id);

    //前台分类
    List<FirstPageBlog> getByTypeId(Long typeId);

    //前台标签
    List<FirstPageBlog> getByTagId(Long tagId);

    //归档：博客总数
    Long count();
    List<String> findGroupYear();
    List<DetailedBlog> findByYear(String year);

    //统计每月的博客数量
    int insertCount(Count count);
    int deleteCount(Count count);
    List<String> findGroupYear2();
    List<Count> findByYear2(String year);
}
