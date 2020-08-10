package net.skhu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.VO.Student;
import net.skhu.mapper.StudentMapper;

@Controller
@RequestMapping("/student") //짝짓기 후에,url에 쓰임.
public class StudentController {

    @Autowired StudentMapper studentMapper; //mapper 만들어서 자동으로 멤버변수에 대입.

    @RequestMapping("list") // => /student/list
    public String list(Model model) { //view한테 주삼
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students", students);
        return "student/list"; //veiw이름.
    }


    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Model model, Student student) {
        studentMapper.insert(student);
        return "redirect:list";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        Student student = studentMapper.findOne(id);
        model.addAttribute("student", student);
        return "student/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String edit(Model model, Student student) {
        studentMapper.update(student);
        return "redirect:list";
    }

    @RequestMapping("delete")
    public String delete(Model model, @RequestParam("id") int id) {
        studentMapper.delete(id);
        return "redirect:list";
    }
}
