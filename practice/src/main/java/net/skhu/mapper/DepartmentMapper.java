package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Department; //department dto연결

@Mapper
public interface DepartmentMapper {
 //   Department findOne(int id); //id값으로 찾기
  //  Department findByStudentNumber(String departmentName); //departmentName 값으로 찾기
    List<Department> findAll(); //모든 값. (전체)
  
}

