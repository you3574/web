package net.skhu.VO;

public class MyCourse {
	int id;
	int year;
	String semester;
	String courseId;
	String subjectName;
	String category;
	int credits;
	String grade;
	String userId;
	
	
	
	public MyCourse(int id, int year, String semester, String courseId, String subjectName, String category,
			int credits, String grade, String userId) {
		super();
		this.id = id;
		this.year = year;
		this.semester = semester;
		this.courseId = courseId;
		this.subjectName = subjectName;
		this.category = category;
		this.credits = credits;
		this.grade = grade;
		this.userId = userId;
	}
	
	public MyCourse(int year, String semester, String courseId, String subjectName, String category,
			int credits, String grade, String userId) {
		
		this.year = year;
		this.semester = semester;
		this.courseId = courseId;
		this.subjectName = subjectName;
		this.category = category;
		this.credits = credits;
		this.grade = grade;
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
