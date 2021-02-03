package com.personblog.service.impl;

import com.personblog.dao.BlogMapper;
import com.personblog.dao.CommentMapper;
import com.personblog.dao.TypeMapper;
import com.personblog.entity.Blog;
import com.personblog.entity.Count;
import com.personblog.entity.Tag;
import com.personblog.exception.NotFoundException;
import com.personblog.service.BlogService;
import com.personblog.util.MarkdownUtils;
import com.personblog.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
//@Slf4j
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentMapper commentMapper;

    //1、查询文章列表
    @Override
    public List<BlogQuery> getAllBlog() {
        return blogMapper.getAllBlogQuery();
    }

    //2、新增文章
    @Override
    public Boolean saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setCommentCount(0);
        blog.setViews(0);
        //新增文章
        blogMapper.saveBlog(blog);
        //新增标签
        saveTags(blog);
        return true;
    }
    //把标签保存在中间表中
    //先判断 tag是否为空，不为空，通过获取到的blogId保存在中间表中，
    //由于tagId有多个值，循环遍历取得每个tagId保存在中间表中
    private void saveTags(Blog blog){
        List<Tag> list = blog.getTags();
        if (list==null)
            return;
        BlogAndTag bt = new BlogAndTag();
        bt.setBlogId(blog.getId());
        for (Tag tag : list) {
            bt.setTagId(tag.getId());
            blogMapper.saveBlogAndTag(bt);
        }
    }

    //3、根据title和type搜索对应博客列表
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogMapper.searchByTitleOrType(searchBlog);
    }


    //4、删除博客，先删除子表数据(blog_tag表的数据，评论的数据)，再删除父表的数据
    @Override
    public int deleteBlog(Long id) {
        blogMapper.deleteBlogAndTag(id);
        //通过博客id找出所有的评论id
        List<Long> cids= commentMapper.findIdsByBlogId(id);
        //遍历评论id,并删除
        for(Long cid:cids){
            commentMapper.deleteComment(cid);
        }
        return blogMapper.deleteBlog(id);

    }

    //5、修改博客
    @Override
    public Boolean updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogMapper.deleteBlogAndTag(blog.getId());
        blogMapper.updateBlog(blog);
        saveTags(blog);
        return true;
    }
    //根据id查询博客信息，在修改页面做一个回显
    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    //前台系统
    //首页博客信息
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogMapper.getFirstPageBlog();
    }

    //推荐
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            if (recommendBlog.isRecommend()) {
                allRecommendedBlog.add(recommendBlog);
            }
        }
        return allRecommendedBlog;
    }

    //全局搜索
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    //博客详情：文章访问数量自增 + 评论数量更新
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        //markdown语法
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogMapper.updateViews(id);
        //文章评论数量更新
        blogMapper.getCommentCountById(id);
        return detailedBlog;
    }

    //前台分类
    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    //前台标签
    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }

    //归档：博客总数
    @Override
    public Long count() {
        return blogMapper.count();
    }

    @Override
    public Map<String, List<Count>> archiveBlog2(Count count) {
        blogMapper.deleteCount(count);
        blogMapper.insertCount(count);
        List<String> years = blogMapper.findGroupYear2();
        Map<String, List<Count>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear2(year));
        }
        //log.info(String.valueOf(map));
        return map;
    }
    @Override
    public Map<String, List<DetailedBlog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Map<String, List<DetailedBlog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear(year));
        }

        return map;
    }
}
