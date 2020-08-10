package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.model.Option;
import net.skhu.model.Pagination;

@Mapper
public interface SearchMapper {

	//나타낼 페이지의 조건을 나타내는 Pagination객체에 맞는 학생목록 반환
	List<net.skhu.dto.Student> findAll(Pagination pagination);

	//Pagination객체가 나타내는 조건에 맞는 전체 학생 수
	int count(Pagination pagination);

	Option[] category = { new Option(0, "검색안됨"), new Option(1, "학번"),
            new Option(2, "이름"), new Option(2, "학과")};
	Option[] orderBy = { new Option(0, "ID오름차순"), new Option(1, "ID내림차순"),
           new Option(2, "학번") };

}
