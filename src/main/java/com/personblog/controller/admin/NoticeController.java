package com.personblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personblog.entity.Notice;
import com.personblog.entity.Tag;
import com.personblog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notices")
    public String list(Model model,
                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 5,orderBy);
        List<Notice> list = noticeService.selectAllNotice();
        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/notices";
    }

    @GetMapping("/notices/input")
    public String input(Model model){
        model.addAttribute("notice", new Notice());
        return "admin/notices-input";
    }

    @PostMapping("/notices")
    public String post(@Validated Notice notice, RedirectAttributes attributes){
        Notice notice1 = noticeService.selectNoticeByContent(notice.getContent());
        if(notice1 != null){
            attributes.addFlashAttribute("message", "不能添加重复的通知");
            return "redirect:/admin/notices/input";
        }
        int t = noticeService.insertNotice(notice);
        if(t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/notices";
    }

    @GetMapping("/notices/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("notice", noticeService.selectNoticeById(id));
        return "admin/notices-input";
    }

    @PostMapping("/notices/{id}")
    public String editPost(@Validated Notice notice, RedirectAttributes attributes){
        Notice notice1 = noticeService.selectNoticeByContent(notice.getContent());
        if(notice1 != null){
            attributes.addFlashAttribute("message", "不能添加重复的通知");
            return "redirect:/admin/notices/input";
        }
        int t = noticeService.updateNotice(notice);
        if(t == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/notices";
    }

    @GetMapping("/notices/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        noticeService.deleteNotice(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/notices";
    }

}
