package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.domain.Employees;
import net.skhu.repository.EmployeesRepository;

@Controller
@RequestMapping("exam")
public class API2Controller {

	@Autowired
	EmployeesRepository employeesRepository;

	@RequestMapping("list1")
	public String list(Model model) {
		List<Employees> list = employeesRepository.findEmployees();
		model.addAttribute("list", list);
		System.out.println(list);
		return "exam/list1";
	}

}
