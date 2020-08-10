package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.book;
import net.skhu.dto.category;
import net.skhu.dto.publisher;
import net.skhu.mapper.bookMapper;
import net.skhu.mapper.categoryMapper;
import net.skhu.mapper.publisherMapper;

@Controller
@RequestMapping("/book") //짝짓기 후에,url에 쓰임.
public class bookController {

    @Autowired bookMapper bookMapper; //mapper 만들어서 자동으로 멤버변수에 대입.
    @Autowired categoryMapper categoryMapper;
    @Autowired publisherMapper publisherMapper;


    @RequestMapping("list2")
    public String list2(Model model) {
        model.addAttribute("books", bookMapper.findAllWithbooks());
        return "book/list2";
    }

    @RequestMapping("list1") // => /book/list
    public String list1(Model model) { //view한테 주삼
        List<book> books = bookMapper.findAll();
        model.addAttribute("books", books);
        return "book/list1"; //veiw이름.
    }

    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Model model) {
        book book = new book();
        List<category> categorys = categoryMapper.findAll();
        List<publisher> publishers = publisherMapper.findAll();
        model.addAttribute("books", bookMapper.findAllWithbooks());
        model.addAttribute("book", book);
        model.addAttribute("categorys", categorys); //select에 학과명 나열.
        model.addAttribute("publishers", publishers);
        return "book/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Model model, book book) {
        bookMapper.insert(book);
        return "redirect:list1";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        book book = bookMapper.findOne(id);
        List<category> categorys = categoryMapper.findAll();
        List<publisher> publishers = publisherMapper.findAll();
       // List<publisher> publishers = publisherMapper.findAll2();
        model.addAttribute("book", book);
        model.addAttribute("categorys", categorys);
        model.addAttribute("publishers", publishers);
        model.addAttribute("books", bookMapper.findAllWithbooks());

        return "book/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String edit(Model model, book book) {
        bookMapper.update(book);
        return "redirect:list1";
    }

    @RequestMapping("delete")
    public String delete(Model model, @RequestParam("id") int id) {
        bookMapper.delete(id);
        return "redirect:list1";
    }
}
