package com.bin.myblog.controller;

import com.bin.myblog.common.Page;
import com.bin.myblog.pojo.Type;
import com.bin.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String toTypes(@RequestParam(value = "currentPage" ,required = false ,defaultValue = "1") Integer currentPage
            , @RequestParam(value = "currentCount" ,required = false ,defaultValue = "10") Integer currentCount
            , Model model){
        Page<Type> typePage = new Page<>(currentPage, currentCount);
        List<Type> types = typeService.listType(typePage.getStartIndex(), typePage.getCurrentCount());
        System.out.println(types);
        typePage.setBeans(types);
        typePage.setTotalCount(typeService.countType());
        model.addAttribute("page",typePage);
        return "admin/types";
    }

    @GetMapping("/input")
    public String toInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/{id}/input")
    public String toUpdateType(@PathVariable("id") Long id,Model model){
        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/types-input";
    }

    @PostMapping("/saveType")
    public String saveType(String typename, RedirectAttributes attributes){
        Type type = typeService.getTypeByName(typename);
        if (type != null){
            attributes.addFlashAttribute("message","该分类已存在！！！");
            return "redirect:/type/input";
        }else {
            Type t = new Type();
            t.setTypename(typename);
            typeService.saveType(t);
            attributes.addFlashAttribute("message","新增分类成功！！！");
            return "redirect:/type/types";
        }
    }

    @PostMapping("/updateType/{id}")
    public String updateType(Type type ,RedirectAttributes attributes){
        Type t = typeService.getTypeByName(type.getTypename());
        if (t != null){
            attributes.addFlashAttribute("message","该分类已存在！！！");
            return "redirect:/type/input";
        }else {
            typeService.updateType(type);
            attributes.addFlashAttribute("message","分类更新成功！！！");
            return "redirect:/type/types";
        }
    }

    @GetMapping("/deleteType/{id}")
    public String deleteType(@PathVariable("id") Long id,RedirectAttributes attributes){
        Integer index = typeService.deleteType(id);
        if (index==1){
            attributes.addFlashAttribute("message","分类删除成功！！！");
        }else {
            attributes.addFlashAttribute("message","分类删除失败！！！");
        }
        return "redirect:/type/types";
    }
}
