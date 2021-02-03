package com.personblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personblog.entity.Blog;
import com.personblog.entity.Comment;
import com.personblog.entity.Tag;
import com.personblog.entity.Type;
import com.personblog.service.*;
import com.personblog.vo.DetailedBlog;
import com.personblog.vo.FirstPageBlog;
import com.personblog.vo.RecommendBlog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiModel(value = "首页展示控制层")
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    // @Autowired
    // private TagService tagService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NoticeService noticeService;


    @ApiModelProperty(value = "首页列表分页展示")
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 4);
        List<FirstPageBlog> firstPageBlogList = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendBlogList = blogService.getRecommendedBlog();
        //首页分类列表
        List<Type> typeList = typeService.selectAllTypeAndBlog();
        // List<Tag> tagList = tagService.selectAllTagAndBlog();
        //分页
        PageInfo<FirstPageBlog> PageInfo = new PageInfo<>(firstPageBlogList);
        model.addAttribute("pageInfo",PageInfo);
        model.addAttribute("types",typeList);

        // model.addAttribute("tags", tagList);
        model.addAttribute("recommendBlogs",recommendBlogList);
        model.addAttribute("notices",noticeService.selectAllNotice());
        return "index";
    }

    @ApiModelProperty(value = "根据id查询博客详情")
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> commentList = commentService.listCommentByBlogId(id);
        model.addAttribute("blog",detailedBlog);
        model.addAttribute("comments",commentList);
        return "blog";
    }

    @ApiModelProperty(value = "全局搜索博客并分页展示")
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 4);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }
}
