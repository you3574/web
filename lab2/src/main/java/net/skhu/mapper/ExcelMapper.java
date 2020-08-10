package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.VO.Course;
import net.skhu.VO.MyCourseRecord;

@Mapper
public interface ExcelMapper {

	int setMyRecord(List<MyCourseRecord> myList);
	int setAllCourse(List<Course> list);
}
