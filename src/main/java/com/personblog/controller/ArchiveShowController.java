package com.personblog.controller;

import com.github.pagehelper.PageHelper;
import com.personblog.entity.Count;
import com.personblog.service.BlogService;
import com.personblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model, Count count){
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.count());
        model.addAttribute("archiveMap2", blogService.archiveBlog2(count));
        return "archives";
    }
}