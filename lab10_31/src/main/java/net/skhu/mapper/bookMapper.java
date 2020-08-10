package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.skhu.dto.book; //db연동

@Mapper
public interface bookMapper {
    List<book> findAllOrderBy(@Param("count") int count, @Param("order") String order);
    book findOne(int id); //id값으로 찾기
    book findBybookTitle(String title); //bookNumber 값으로 찾기
    List<book> findAll(); //모든 값. (전체)
    void insert(book book); //값 삽입
    void update(book book); // 업데이트
    void delete(int id); //삭제
}

