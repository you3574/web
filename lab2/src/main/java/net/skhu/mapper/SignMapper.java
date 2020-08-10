package net.skhu.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.VO.Admin;
import net.skhu.VO.Student;
import net.skhu.dto.SignUpDto;

@Mapper
public interface SignMapper {

    int signup(SignUpDto signUpDto);
    Admin adminLogin(Map<String, Object> loginMap);
    Student studentLogin(Map<String, Object> loginMap);
    int studentChange(Student dto);

}
