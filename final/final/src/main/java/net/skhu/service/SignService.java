package net.skhu.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.User;
import net.skhu.dto.SignUser;
import net.skhu.mapper.SignMapper;

@Service
public class SignService {
	
	@Autowired
	private SignMapper signMapper;
	
	//사용자 생성
	public int register(SignUser signUser) {
		return signMapper.register(signUser);
	}
	//로그인
	public User login(Map<String, Object> loginMap) {
		
		return signMapper.login(loginMap);
	}
	//사용자 정보 수정
	public void update(User user) {
		signMapper.update(user);
	}
	public void insert(User user) {
		signMapper.insert(user);
		
	}

}
