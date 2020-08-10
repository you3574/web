package net.skhu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.VO.MyCourse;
import net.skhu.VO.User;
import net.skhu.dto.SignUser;

@Mapper
public interface SignMapper {

	//사용자 생성
	int register(SignUser signUser);
	//로그인해서 해당객체 찾기
	User login(Map<String, Object> loginMap);
	//사용자 정보 수정
	void update(User user);
	//유저 등록
	void insert(User user);
	
	User getMyCourses(String userId);
	
}
