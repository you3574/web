package net.skhu.VO;

//학생 관리하는 테이블
//id,비밀번호,학번,이름,학과,담당교수id,학년,나의 전공학점,나의 교양학점,사회봉사여부를 저장
public class Student {

	private int id;
	private String pw;
	private String studentId;
	private String name;
	private int departmentId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

}
