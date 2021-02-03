package com.personblog.service;


import com.personblog.entity.Blog;
import com.personblog.entity.Count;
import com.personblog.vo.*;

import java.util.List;
import java.util.Map;

public interface BlogService {

    //1、获取博客列表
    List<BlogQuery> getAllBlog();

    //2、根据title和type搜索对应博客列表
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    //3、添加博客
    Boolean saveBlog(Blog blog);

    //4、删除博客
    int deleteBlog(Long id);

    //5、修改博客
    Boolean updateBlog(Blog blog);
    //根据id查询博客信息，在修改页面做一个回显
    Blog getBlogById(Long id);


    //前端展示

    //首页展示
    List<FirstPageBlog> getAllFirstPageBlog();

    //推荐
    List<RecommendBlog> getRecommendedBlog();

    //全局搜索
    List<FirstPageBlog> getSearchBlog(String query);

    //详情
    DetailedBlog getDetailedBlog(Long id);

    //前台分类
    List<FirstPageBlog> getByTypeId(Long typeId);

    //前台标签
    List<FirstPageBlog> getByTagId(Long tagId);

    //归档
    //统计博客总数
    Long count();

    //用map存储年份和博客的其他信息
    Map<String,List<DetailedBlog>> archiveBlog();

    Map<String,List<Count>> archiveBlog2(Count count);
}
