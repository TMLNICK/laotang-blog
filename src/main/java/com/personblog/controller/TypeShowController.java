package com.personblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personblog.entity.Type;
import com.personblog.service.BlogService;
import com.personblog.service.TypeService;
import com.personblog.vo.FirstPageBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String typeShow(Model model, @PathVariable Long id,
                           @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        List<Type> types = typeService.selectAllTypeAndBlog();
        //-1表示从首页导航点进来
        if(id == -1){
            id = types.get(0).getId();
        }
        model.addAttribute("types",types);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum,4);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        PageHelper.clearPage();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
