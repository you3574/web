package net.skhu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.Student;
import net.skhu.mapper.SignMapper;

@Service
public class SignService {

	@Autowired
	private SignMapper signMapper;

	public Student studentLogin(Map<String, Object> loginMap) {
		return signMapper.studentLogin(loginMap);
	}



}
