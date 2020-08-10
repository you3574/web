package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.VO.Student;

@Mapper
public interface StudentMapper {
    Student findOne(int id); //id값으로 찾기
    Student findByStudentNumber(String studentNumber); //studentNumber 값으로 찾기
    List<Student> findAll(); //모든 값. (전체)
    void insert(Student student); //값 삽입
    void update(Student student); // 업데이트
    void delete(int id); //삭제
}

