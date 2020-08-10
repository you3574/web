package net.skhu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.skhu.VO.MyCourse;
import net.skhu.VO.User;

import net.skhu.service.ExcelService;
import net.skhu.service.SignService;

@Controller

public class MainController<Pagination> {

	@Autowired
	private SignService signService;

	@Autowired 
	private ExcelService excelService;

	@GetMapping("/")
	public ModelAndView LoginPage() {

		ModelAndView response = new ModelAndView("/index");

		return response;
	}

	@PostMapping("/")
	public String LoginPage1() throws Exception{


		return "redirect:login";

	}
	@GetMapping("login")
	public String sadf() {

		return "login";
	}
	@PostMapping("/login")
	public String Login(@RequestParam(value="loginId") String loginId,
			@RequestParam(value="loginPw") String loginPw,
			HttpSession session) throws Exception{

		//로그인한 아이디와 비밀번호 맵으로 매핑
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("loginId", loginId);
		loginMap.put("loginPw", loginPw);

		User user = signService.login(loginMap);

		//로그인한 정보를 바탕으로 유저 객체 세션에 저장
		session.setAttribute("loginUser", user);

		return "redirect:main";

	}

	@GetMapping("register")
	public String Register(Model model) {

		User user = new User();

		model.addAttribute("user", user);

		return "student/register";


	}
	@PostMapping("register")
	//@ResponseBody
	public String Register(User user) throws Exception{

		signService.insert(user);


		return "redirect:login";
	}

	@GetMapping("main")
	public String Main(Model model,HttpSession session) {

		User user = (User)session.getAttribute("loginUser");
		
		List<MyCourse> myCourses = excelService.getMyCourses(user.getId());

		model.addAttribute("user", user);
		model.addAttribute("myCourses", myCourses);

		return "student/main";


	}

	@PostMapping("main")
	public String Main(Model model, @RequestParam("file") MultipartFile file,HttpSession session) throws Exception {

		User user = (User)session.getAttribute("loginUser");
		List<MyCourse> myCourses = excelService.excelToObject(file.getInputStream(),user);
		excelService.setMyCourses(myCourses);


		return "redirect:main";
	}

	@RequestMapping("search")
    public String list1(Pagination pagination, Model model,HttpSession session) {
		
		User user = (User)session.getAttribute("loginUser");
		List<MyCourse> myCourses = excelService.getMyCourses(user.getId());

		
		model.addAttribute("myCourses", myCourses);
        return "student/search";
    }


	@GetMapping("update")
	public String Update(Model model,HttpSession session) {

		User user = (User)session.getAttribute("loginUser");
		

		model.addAttribute("user", user);

		return "student/update";


	}
	@PostMapping("update")
	public String Update(Model model,User user) throws Exception{

		signService.update(user);


		return "redirect:main";
	}

}
