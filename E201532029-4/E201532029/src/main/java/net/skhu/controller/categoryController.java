package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.dto.category;
import net.skhu.mapper.categoryMapper;

@Controller
@RequestMapping("/category") //짝짓기 후에,url에 쓰임.
public class categoryController {

    @Autowired categoryMapper categoryMapper;

    @RequestMapping("list")
    public String list(Model model) {
        List<category> categorys = categoryMapper.findAll();
        model.addAttribute("categorys", categorys);
        return "category/list";
    }


}
