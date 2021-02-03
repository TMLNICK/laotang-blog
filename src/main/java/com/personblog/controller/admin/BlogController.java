package com.personblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personblog.entity.Blog;
import com.personblog.entity.Tag;
import com.personblog.entity.Type;
import com.personblog.entity.User;
import com.personblog.service.BlogService;
import com.personblog.service.TagService;
import com.personblog.service.TypeService;
import com.personblog.vo.BlogQuery;
import com.personblog.vo.SearchBlog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
@ApiModel(value = "后台博客控制层")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @ApiModelProperty(value = "分页查询文章列表")
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types",typeService.selectAllType());
        return "admin/blogs";
    }


    @ApiModelProperty(value = "搜索文章分页显示")
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 5);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        //返回的是blogList片段
        return "admin/blogs :: blogList";
    }

    @ApiModelProperty(value = "返回新增页面")
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("tags", tagService.selectAllTag());
        model.addAttribute("types",typeService.selectAllType());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }


    @ApiModelProperty(value = "新增博客")
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //设置type
        blog.setType(typeService.selectTypeById(blog.getType().getId()));
        //设置tag(传入多个tagId,通过getTagByString方法得到tagId)
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        //设置 typeId
        blog.setTypeId(blog.getType().getId());
        //从session中获取user对象
        blog.setUser((User) session.getAttribute("user"));
        //设置 user id
        blog.setUserId((long) 0);

        Boolean b = blogService.saveBlog(blog);
        if(b){
            attributes.addFlashAttribute("message", "新增成功");
        }else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/blogs";
    }

    @ApiModelProperty(value = "根据id删除博客")
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


    @ApiModelProperty(value = "返回修改页面")
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Blog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.selectAllType();
        List<Tag> allTag = tagService.selectAllTag();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        model.addAttribute("tags", allTag);
        return "admin/blogs-input";
    }


    @ApiModelProperty(value = "根据id修改博客信息")
    @PostMapping("/blogs/{id}")
    public String editPost(Blog blog, RedirectAttributes attributes, HttpSession session,@PathVariable Long id){
        blog.setType(typeService.selectTypeById(blog.getType().getId()));
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        //设置 type id
        blog.setTypeId(blog.getType().getId());
        //从session中获取user对象
        blog.setUser((User) session.getAttribute("user"));
        //设置 user id
        blog.setUserId((long) 0);
        Boolean b = blogService.updateBlog(blog);
        if(b){
            attributes.addFlashAttribute("message", "修改成功");
        }else {
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/blogs";
    }

}
