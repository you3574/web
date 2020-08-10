package net.skhu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.Admin;
import net.skhu.VO.Student;
import net.skhu.dto.SignUpDto;
import net.skhu.mapper.SignMapper;

@Service
public class SignService {

	@Autowired
	private SignMapper signMapper;

	public int SignUp(SignUpDto signUpDto) {
		return signMapper.signup(signUpDto);
	}

	public Admin adminLogin(Map<String, Object> loginMap) {
		return signMapper.adminLogin(loginMap);
	}

	public Student studentLogin(Map<String, Object> loginMap) {
		return signMapper.studentLogin(loginMap);
	}

	public int studentChange(Student dto) {
		return signMapper.studentChange(dto);
	}

}
