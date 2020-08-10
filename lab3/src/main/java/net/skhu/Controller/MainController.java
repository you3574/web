package net.skhu.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.skhu.Service.SignService;
import net.skhu.VO.Student;
import net.skhu.dto.SignUpDto;

@Controller
public class MainController {

	@Autowired
	private SignService signService;
	@Autowired

	@GetMapping("/")
	public ModelAndView LoginPage() {

		ModelAndView response = new ModelAndView("/login");

		return response;
	}

	@PostMapping("login")
	public String Login(@RequestParam(value = "usernumber") String usernumber,
			@RequestParam(value = "pass") String pass, HttpSession session) throws Exception {

		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("usernumber", usernumber);
		loginMap.put("pass", pass);

		Student student = signService.studentLogin(loginMap);

		if (student != null) {
			session.setAttribute("loginUser", student); // loginUser에 알맞은 객체 넣기.
			return "redirect:student";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("admin")
	public String Admin() {
		return "admin/admin";

	}

	@GetMapping("student")
	public String Student() {
		return "student/student";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // session 전체 초기화

		return "redirect:/";
	}

	@PostMapping("signup")
	@ResponseBody
	public boolean SignUp(@RequestBody SignUpDto signUpDto) throws Exception {

		int temp = signService.SignUp(signUpDto);
		if (temp > 0)
			return true;
		else
			return false;
	}

	@PostMapping("studentChange")
	@ResponseBody
	public boolean studentChange(@RequestBody String password, HttpSession session) throws Exception {

		Student student = (Student) session.getAttribute("loginUser");
		student.setPw(password);

		int temp2 = signService.studentChange(student);
		if (temp2 > 0)
			return true;
		else
			return false;
	}

	//
	@GetMapping("excel")
	public String excel() {
		return "excel";
	}

}
