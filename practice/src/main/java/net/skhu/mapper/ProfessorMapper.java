package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Professor; //department dto연결

@Mapper
public interface ProfessorMapper {
 //   Department findOne(int id); //id값으로 찾기
  //  Department findByStudentNumber(String departmentName); //departmentName 값으로 찾기
    List<Professor> findAll(); //모든 값. (전체)
    
    Professor findByProfessorId(int id);
    List<Professor> findBydepartmentId(int departmentId); // departmentId로 찾기
    void update(Professor professor); // 업데이트
  
}
