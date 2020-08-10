package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.skhu.VO.MyCourse;


@Mapper
public interface ExcelMapper {

	//엑셀파일에서 저장한 객체 디비에 저장하기
	void setMyCourses(List<MyCourse> list);
	
	//디비에 저장된 내 수강내역 조회하기
	
	List<MyCourse> getMyCourses(String userId);

}
