package net.skhu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;
import net.skhu.service.RegistryService;
import net.skhu.service.SignService;

@Controller
public class ExcelController {

	@Autowired
	private SignService signService;
	@Autowired
	private RegistryService registryService;
	@Autowired
	private StudentMapper studentmapper;

	@GetMapping("/")
	public String Main() {

		return "index";
	}



	@GetMapping("student/excel")
	public String excel() {
		return "student/excel";
	}

	@PostMapping("/studentrecord")
	@ResponseBody
	public ResponseEntity<String> AllCourse(@RequestParam(value="allStudent") MultipartFile allStudent) throws Exception{
		System.out.println("studentrecord");
		List<Student> list = new ArrayList<>();
		if ( (allStudent.getOriginalFilename().indexOf(".xlsx") > -1) ) {

			list = registryService.studentRecord(allStudent.getInputStream());

			for(int i=0 ; i<list.size() ; i++) {
				studentmapper.insert(list.get(i));
			}

		}

		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

	}

	@GetMapping("student/login")
	public String Login() throws Exception{
		return "student/login";
	}


	@PostMapping("student/login")
	public String Login(@RequestParam(value="studentnumber") String studentnumber,
			@RequestParam(value="name") String name,
			HttpSession session) throws Exception{

		System.out.println(studentnumber);
		System.out.println(name);

		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("studentnumber", studentnumber);
		loginMap.put("name", name);

		Student student = signService.studentLogin(loginMap);

		System.out.println(student.getName());


		if(student != null) {

			session.setAttribute("loginUser", student); //loginUser에 알맞은 객체 넣기.
			System.out.println("로그인되었습니다.");
			return "redirect:/student/list";
		}else {
			return "redirect:/";
		}
	}

	@GetMapping("student")
	public String Student() {
		return "student/list";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();						//session 전체 초기화

		return "redirect:/";
	}

}
