package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Department;
import net.skhu.dto.Student;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired StudentMapper studentMapper;
    @Autowired DepartmentMapper departmentMapper;

    @RequestMapping("list")
    public String list(Model model) {
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }
    
    /*
    @RequestMapping(value="list", method=RequestMethod.POST)
    public String SelectOne(Model model, @RequestParam("inputNumber") String inputNumber) {
        //List<Student> students = studentMapper.findAll();
        //model.addAttribute("students", students);
    	System.out.println(inputNumber);
    	List<Student> students = studentMapper.findByStudentNumber(inputNumber);
    	model.addAttribute("students", students);
    	//System.out.println(students.get(0).getId());
        return "student/list";
    }
    */
    @RequestMapping(value="list", method=RequestMethod.POST)
    public String SelectOne(Model model, @RequestParam(value="inputText", required=false) String inputText,
    		@RequestParam("type") String type ) {
    	List<Student> students ;
    	if(type.equals("name")) {
    		students = studentMapper.findByStudentName(inputText);
        	model.addAttribute("students", students);
    	}else if(type.equals("studentNumber")) {
    		students = studentMapper.findByStudentName(inputText);
        	model.addAttribute("students", students);
        	
    	}else if(type.equals("departmentName")) {
    		students = studentMapper.findByDepartmentName(inputText);
        	model.addAttribute("students", students);
    	}else {
    		students = studentMapper.findAll();
            model.addAttribute("students", students);
    	}

    	//List<Student> students = studentMapper.findByStudentNumber(inputNumber);
    	//model.addAttribute("students", students);
        return "student/list";
    }
    
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Model model) {
        Student student = new Student();
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
        return "student/edit"; //view prefix / seffix
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Model model, Student student) {
        studentMapper.insert(student);
        return "redirect:list";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        Student student = studentMapper.findOne(id);
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
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
