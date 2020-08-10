package net.skhu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.VO.CategoryChange;
import net.skhu.VO.CulturalRequire;
import net.skhu.VO.MajorRequire;
import net.skhu.VO.MyCourseRecord;
import net.skhu.VO.Student;

@Mapper
public interface StudentMapper {


	List<Student> getAllstudent();

	//학생 테이블에 존재하는 모든 학생의 학번만을 조회
	List<String> getAllstudentId();

	//학생의 기본키를 가지고 해당 학생 한 명을 조회하여 그 객체 반환
	Student getAStudent(int id);

	//학생의 학번을 가지고  해당 학생 한 명을 조회하여 그 객체 반환
	Student findByStudentId(int studentId);
	//
	//	//학생 테이블에 존재하는 모든 학생의 객체들을 반환
	//	List<Student> findAll();
	//
	//	//학생 객체를 학생 테이블에 추가
	//	void insert(Student student);
	//
	//	//학생테이블에 존재하는 학생 정보 수정
	//	void update(Student student);
	//
	//	//해당 id의 학생 정보 삭제
	//	void delete(int id);

	List<MyCourseRecord> getStudentRecord(String studentId);
	List<MyCourseRecord> getStudentRecordPass(String studentId);
	String getMajorCourseName(MyCourseRecord record);
	String getCulturalCourseName(MyCourseRecord record);
	String getTableName(final String condition, int departId, String year);
	String getCode(final String condition, int departId, String year);
	MajorRequire getMajorRequire(String tableName, String code, String course);
	String getMajorName(Map<String, Object> map);
	String getMajorName2(Map<String, Object> map);

	String getMajorCourseName2(String courseId, int year, String semester, int departmentId);
	String getMajorRequireCourseName(String courseId, int year, String semester, int departmentId);
	String getDoubleCourseName(MyCourseRecord record, int departmentId);

	String getCulturalTableName(final String condition, int departId, String year);
	String getCulturalCode(final String condition, int departId, String year);
	CulturalRequire getCulturalRequire(String tableName, String code);

	int deleteStudentRecord(String studentId);

	Integer getRecordId(int recordId);
	int setCategotyChange(CategoryChange categoryChange);
	List<CategoryChange> getStatus0(String studentId);
	List<CategoryChange> getStatus1(String studentId);
	List<CategoryChange> getStatus2(String studentId);
}
