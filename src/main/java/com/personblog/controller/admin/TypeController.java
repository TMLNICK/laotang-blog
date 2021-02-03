package com.personblog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personblog.entity.Type;
import com.personblog.service.TypeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@ApiModel(value = "分类控制层")
public class TypeController {

    @Autowired
    private TypeService typeService;


    //pageNum 表示当前页，默认设置为1
    //pageSize 表示每页的数量
    //pageInfo 用来渲染前端页面，PageHelper还有一些其他参数在前端页面中使用到
    @ApiModelProperty(value = "分页查询分类列表")
    @GetMapping("/types")
    public String list(Model model,
                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 5,orderBy);
        List<Type> list = typeService.selectAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @ApiModelProperty(value = "返回新增分类页面")
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @ApiModelProperty(value = "新增分类")
    @PostMapping("/types")
    public String post(@Validated Type type, RedirectAttributes attributes){
        Type type1 = typeService.selectTypeByName(type.getName());
        if(type1 != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.insertType(type);
        if(t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }


    @ApiModelProperty(value = "返回修改分类页面")
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.selectTypeById(id));
        return "admin/types-input";
    }

    @ApiModelProperty(value = "修改分类")
    @PostMapping("/types/{id}")
    public String editPost(@Validated Type type, RedirectAttributes attributes) {
        Type type1 = typeService.selectTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }


    @ApiModelProperty(value = "删除分类")
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
