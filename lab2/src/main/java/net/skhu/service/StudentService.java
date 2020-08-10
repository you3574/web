package net.skhu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.CategoryChange;
import net.skhu.VO.CulturalRequire;
import net.skhu.VO.MajorRequire;
import net.skhu.VO.MyCourseRecord;
import net.skhu.VO.Student;
import net.skhu.VO.SubjectColor;
import net.skhu.mapper.StudentMapper;

@Service
public class StudentService {

	private static final String courseA ="전공기초";
	private static final String courseB ="전공심화";
	private static final String courseC ="타과복수전공";
	private static final String courseD ="타과부전공";
	private static final String majorCondition0 = "주전공";
	private static final String majorCondition1 = "타과복수전공";
	private static final String majorCondition2 = "타과부전공";
	private static final String majorCondition3 = "복수전공";
	private static final String majorCondition4 = "부전공";
	private static final String A = "전필";
	private static final String B = "전선";
	private static final String C = "교필";
	private static final String D = "교선";
	private static final String E = "복필";
	private static final String F = "복선";
	private static final String G = "부필";
	private static final String H = "부선";

	@Autowired
	private StudentMapper studentmapper;

	public List<String> getAllstudentId(){

		return studentmapper.getAllstudentId();
	}

	public List<net.skhu.VO.Student> getAllstudent(){
		return studentmapper.getAllstudent();
	}

	public Student getAStudent(int id) {

		return studentmapper.getAStudent(id);
	}

	public Student findByStudentId(int id) {

		return studentmapper.findByStudentId(id);
	}

	//	public List<Student> getfindAll(){
	//		return studentmapper.findAll();
	//	}

	public boolean getStudentRecordData(String studentId) {
		List<MyCourseRecord> list = studentmapper.getStudentRecord(studentId);
		if(list.size() == 0)
			return false;
		else
			return true;
	}

	public boolean deleteStudentRecord(String studentId) {
		int num = studentmapper.deleteStudentRecord(studentId);
		if(num>0)
			return true;
		return false;
	}

	public Map<String, Object> getStudentRecord(String year, Student student) {

		List<MyCourseRecord> list = studentmapper.getStudentRecord(student.getStudentId()); //전체 저장 리스트
		List<MyCourseRecord> majorReqquireList = new ArrayList<>(); //전필 저장 리스트
		List<MyCourseRecord> majorList = new ArrayList<>(); // 전공 저장 리스트
		List<MyCourseRecord> culturalList = new ArrayList<>(); //교양 저장 리스트
		List<MyCourseRecord> doubleReqquireList = new ArrayList<>(); // 복전 또는 부전공 전필 저장 리스트
		List<MyCourseRecord> doubleList = new ArrayList<>(); //복전 또는 부전공 저장 리스트

		int totalSum=0; //전체 학점 저장
		int majorRequqireSum = 0; //전필 수강 학점
		int majorSum=0; //전공 학점 저장
		int culturalSum=0; //교양 학점 저장
		int doubleRequqireSum=0; //복전 또는 부전공 필수 학점 저장
		int doubleSum=0; //복전 또는 부전공 학점 저장

		int total = 0; //졸업 요구 학점
		int totalrequireMajor = 0; //전필 요구 학점
		int totalMajor = 0; //전공 요구 학점
		int totalCultural = 0; //교양 요구 학점
		int totalrequireDouble = 0; //복전 또는 부전공 필수 학점
		int totalDouble = 0; //복전 또는 부전공 요구 학점

		Map<String, Object> tempMap = new HashMap<String, Object>();


		//전공 기초, 전공 심화일 경우
		if(student.getCourse().equals(courseA) || student.getCourse().equals(courseB)) {
			System.out.println("전공기초 또는 전공심화를 계산합니다.");
			for(int i=0 ; i<list.size() ; i++) {

				//논패스나 F가 아닌 경우게 합산 저장
				if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
					totalSum = totalSum + list.get(i).getCredits();
				else //NP거나 F면 계산할 때는 취득 학점을 0으로 해줘야 하니까
					list.get(i).setCredits(0);

				//각각 리스트에 값 추가해주기
				//전필 -> 전필 리스트와 전공 전체 리스트에 둘 다 추가해야함
				if(list.get(i).getCategory().equals(A)) {
					majorReqquireList.add(list.get(i)); //전필 리스트에 추가
					majorRequqireSum = majorRequqireSum + list.get(i).getCredits(); //전필 학점 더하기
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//전선 -> 전공 리스트에만 추가해야함
				else if(list.get(i).getCategory().equals(B)) {
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//교양 -> 교필, 교선 모두 교양 리스트에 추가
				else if(list.get(i).getCategory().equals(C) || list.get(i).getCategory().equals(D)) {

					//이수 변경 신청으로 전공선택인 경우
					if(list.get(i).getException()==1) {
						majorList.add(list.get(i)); //전공 리스트에 추가
						majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
					}else {
						culturalList.add(list.get(i)); //교양 리스트에 추가
						culturalSum = culturalSum + list.get(i).getCredits(); //교양 학점 더하기
					}
				}

			}//for문 종료

			//수료 학점 가져오기
			String tableName = studentmapper.getTableName(majorCondition0, student.getDepartmentId(), year);
			String code = studentmapper.getCode(majorCondition0, student.getDepartmentId(), year);

			MajorRequire temp = studentmapper.getMajorRequire(tableName, code, student.getCourse());

			total = temp.getTotal(); //졸업 요구 학점
			totalrequireMajor = temp.getRequireMajor(); //전필 요구 학점
			totalMajor = temp.getTotalMajor(); //전공 요구 학점
			totalCultural = temp.getTotalCultural(); //교양 요구 학점

			tempMap.put("AllList", list);
			tempMap.put("MajorReqquireList",majorReqquireList);
			tempMap.put("MajorList", majorList);
			tempMap.put("CulturalList", culturalList);

			tempMap.put("totalSum",totalSum);
			tempMap.put("majorRequqireSum",majorRequqireSum);
			tempMap.put("majorSum",majorSum);
			tempMap.put("culturalSum",culturalSum);

			double totalPercentage = Math.round(  (((double)totalSum/total*100)*100) / 100.0  );
			if(totalPercentage>100)
				totalPercentage = 100;
			tempMap.put("totalPercentage",totalPercentage);
			tempMap.put("total",total );

			double majorRequirePercentage = Math.round(  (((double)majorRequqireSum/totalrequireMajor*100)*100) / 100.0  );
			if(majorRequirePercentage>100)
				majorRequirePercentage = 100;
			tempMap.put("majorRequirePercentage",majorRequirePercentage);
			tempMap.put("totalrequireMajor",totalrequireMajor );

			double majorPercentage = Math.round(  (((double)majorSum/totalMajor*100)*100) / 100.0  );
			if(majorPercentage>100)
				majorPercentage = 100;
			tempMap.put("majorPercentage",majorPercentage);
			tempMap.put("totalMajor",totalMajor );

			double culturalPercentage = Math.round(  (((double)culturalSum/totalCultural*100) *100 ) / 100.0  );
			if(culturalPercentage>100)
				culturalPercentage = 100;
			tempMap.put("culturalPercentage",culturalPercentage);
			tempMap.put("totalCultural", totalCultural);

		}else if(student.getCourse().equals(courseC)) { //복수 전공일 경우
			System.out.println("복수전공을 계산합니다.");

			for(int i=0 ; i<list.size() ; i++) {

				//논패스나 F가 아닌 경우게 합산 저장
				if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
					totalSum = totalSum + list.get(i).getCredits();
				else //NP거나 F면 계산할 때는 취득 학점을 0으로 해줘야 하니까
					list.get(i).setCredits(0);

				//각각 리스트에 값 추가해주기
				//전필 -> 전필 리스트와 전공 전체 리스트에 둘 다 추가해야함
				if(list.get(i).getCategory().equals(A)) {
					majorReqquireList.add(list.get(i)); //전필 리스트에 추가
					majorRequqireSum = majorRequqireSum + list.get(i).getCredits(); //전필 학점 더하기
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//전선 -> 전공 리스트에만 추가해야함
				else if(list.get(i).getCategory().equals(B)) {
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//교양 -> 교필, 교선 모두 교양 리스트에 추가
				else if(list.get(i).getCategory().equals(C) || list.get(i).getCategory().equals(D)) {
					//이수 변경 신청으로 전공선택인 경우
					if(list.get(i).getException()==1) {
						majorList.add(list.get(i)); //전공 리스트에 추가
						majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
					}else {
						culturalList.add(list.get(i)); //교양 리스트에 추가
						culturalSum = culturalSum + list.get(i).getCredits(); //교양 학점 더하기
					}
				}//복수전공 필수 -> 복필 리스트와 복전 전체 리스트에 둘 다 추가해야함
				else if(list.get(i).getCategory().equals(E)) {
					doubleReqquireList.add(list.get(i));
					doubleRequqireSum = doubleRequqireSum + list.get(i).getCredits();
					doubleList.add(list.get(i));
					doubleSum = doubleSum + list.get(i).getCredits();
				}//복수전공 선택 ->복수전공 리스트에만 추가
				else if(list.get(i).getCategory().equals(F)) {
					doubleList.add(list.get(i));
					doubleSum = doubleSum + list.get(i).getCredits();
				}

			}//for문 종료

			//수료 학점 가져오기

			String majorTableName = studentmapper.getTableName(majorCondition1, student.getDepartmentId(), year);
			String majorCode = studentmapper.getCode(majorCondition1, student.getDepartmentId(), year);

			String doubleTableName = studentmapper.getTableName(majorCondition3, student.getAnotherMajorDepart(), year);
			String doubleCode = studentmapper.getCode(majorCondition3, student.getAnotherMajorDepart(), year);

			MajorRequire major = studentmapper.getMajorRequire(majorTableName, majorCode, majorCondition1);
			MajorRequire doubleMajor = studentmapper.getMajorRequire(doubleTableName, doubleCode , majorCondition3);

			total = major.getTotal(); //졸업 요구 학점
			totalrequireMajor = major.getRequireMajor(); //전필 요구 학점
			totalMajor = major.getTotalMajor(); //전공 요구 학점
			totalCultural = major.getTotalCultural(); //교양 요구 학점
			totalrequireDouble = doubleMajor.getRequireMajor(); //복전 또는 부전공 필수 학점
			totalDouble = doubleMajor.getTotalMajor(); //복전 또는 부전공 요구 학점

			tempMap.put("AllList", list);
			tempMap.put("MajorReqquireList",majorReqquireList);
			tempMap.put("MajorList", majorList);
			tempMap.put("CulturalList", culturalList);
			tempMap.put("DoubleReqquireList", doubleReqquireList);
			tempMap.put("DoubleList", doubleList);

			tempMap.put("totalSum",totalSum);
			tempMap.put("majorRequqireSum",majorRequqireSum);
			tempMap.put("majorSum",majorSum);
			tempMap.put("culturalSum",culturalSum);
			tempMap.put("doubleRequqireSum",doubleRequqireSum);
			tempMap.put("doubleSum",doubleSum);

			double totalPercentage = Math.round(  (((double)totalSum/total*100)*100) / 100.0  );
			if(totalPercentage>100)
				totalPercentage = 100;
			tempMap.put("totalPercentage",totalPercentage);
			tempMap.put("total",total );

			double majorRequirePercentage = Math.round(  (((double)majorRequqireSum/totalrequireMajor*100)*100) / 100.0  );
			if(majorRequirePercentage>100)
				majorRequirePercentage = 100;
			tempMap.put("majorRequirePercentage",majorRequirePercentage);
			tempMap.put("totalrequireMajor",totalrequireMajor );

			double majorPercentage = Math.round(  (((double)majorSum/totalMajor*100)*100) / 100.0  );
			if(majorPercentage>100)
				majorPercentage = 100;
			tempMap.put("majorPercentage",majorPercentage);
			tempMap.put("totalMajor",totalMajor );

			double culturalPercentage = Math.round(  (((double)culturalSum/totalCultural*100) *100 ) / 100.0  );
			if(culturalPercentage>100)
				culturalPercentage = 100;
			tempMap.put("culturalPercentage",culturalPercentage);
			tempMap.put("totalCultural",totalCultural );

			double doubleRequirePercentage = Math.round(  (((double)doubleRequqireSum/totalrequireDouble*100) *100 ) / 100.0  );
			if(doubleRequirePercentage>100)
				doubleRequirePercentage = 100;
			tempMap.put("doubleRequirePercentage",doubleRequirePercentage);
			tempMap.put("totalrequireDouble",totalrequireDouble );

			double doublePercentage = Math.round(  (((double)doubleSum/totalDouble*100) *100 ) / 100.0  );
			if(doublePercentage>100)
				doublePercentage = 100;
			tempMap.put("doublePercentage",doublePercentage);
			tempMap.put("totalDouble",totalDouble );


		}else if(student.getCourse().equals(courseD)) { //부전공일 경우
			System.out.println("부전공을 계산합니다.");

			for(int i=0 ; i<list.size() ; i++) {

				//논패스나 F가 아닌 경우게 합산 저장
				if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
					totalSum = totalSum + list.get(i).getCredits();
				else //NP거나 F면 계산할 때는 취득 학점을 0으로 해줘야 하니까
					list.get(i).setCredits(0);

				//각각 리스트에 값 추가해주기
				//전필 -> 전필 리스트와 전공 전체 리스트에 둘 다 추가해야함
				if(list.get(i).getCategory().equals(A)) {
					majorReqquireList.add(list.get(i)); //전필 리스트에 추가
					majorRequqireSum = majorRequqireSum + list.get(i).getCredits(); //전필 학점 더하기
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//전선 -> 전공 리스트에만 추가해야함
				else if(list.get(i).getCategory().equals(B)) {
					majorList.add(list.get(i)); //전공 리스트에 추가
					majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
				}//교양 -> 교필, 교선 모두 교양 리스트에 추가
				else if(list.get(i).getCategory().equals(C) || list.get(i).getCategory().equals(D)) {
					//이수 변경 신청으로 전공선택인 경우
					if(list.get(i).getException()==1) {
						majorList.add(list.get(i)); //전공 리스트에 추가
						majorSum = majorSum + list.get(i).getCredits(); //전공 학점 더하기
					}else {
						culturalList.add(list.get(i)); //교양 리스트에 추가
						culturalSum = culturalSum + list.get(i).getCredits(); //교양 학점 더하기
					}
				}//부전공 필수 -> 부필 리스트와 부전 전체 리스트에 둘 다 추가해야함
				else if(list.get(i).getCategory().equals(G)) {
					doubleReqquireList.add(list.get(i));
					doubleRequqireSum = doubleRequqireSum + list.get(i).getCredits();
					doubleList.add(list.get(i));
					doubleSum = doubleSum + list.get(i).getCredits();
				}//부전공 선택 ->부전공 리스트에만 추가
				else if(list.get(i).getCategory().equals(H)) {
					doubleList.add(list.get(i));
					doubleSum = doubleSum + list.get(i).getCredits();
				}
			}//for문 종료

			//수료 학점 가져오기

			String majorTableName = studentmapper.getTableName(majorCondition1, student.getDepartmentId(), year);
			String majorCode = studentmapper.getCode(majorCondition1, student.getDepartmentId(), year);

			String doubleTableName = studentmapper.getTableName(majorCondition4, student.getAnotherMajorDepart(), year);
			String doubleCode = studentmapper.getCode(majorCondition4, student.getAnotherMajorDepart(), year);

			MajorRequire major = studentmapper.getMajorRequire(majorTableName, majorCode, majorCondition1);
			MajorRequire doubleMajor = studentmapper.getMajorRequire(doubleTableName, doubleCode , majorCondition4);

			total = major.getTotal(); //졸업 요구 학점
			totalrequireMajor = major.getRequireMajor(); //전필 요구 학점
			totalMajor = major.getTotalMajor(); //전공 요구 학점
			totalCultural = major.getTotalCultural(); //교양 요구 학점
			totalrequireDouble = doubleMajor.getRequireMajor(); //복전 또는 부전공 필수 학점
			totalDouble = doubleMajor.getTotalMajor(); //복전 또는 부전공 요구 학점

			tempMap.put("AllList", list);
			tempMap.put("MajorReqquireList",majorReqquireList);
			tempMap.put("MajorList", majorList);
			tempMap.put("CulturalList", culturalList);
			tempMap.put("DoubleReqquireList", doubleReqquireList);
			tempMap.put("DoubleList", doubleList);

			tempMap.put("totalSum",totalSum);
			tempMap.put("majorRequqireSum",majorRequqireSum);
			tempMap.put("majorSum",majorSum);
			tempMap.put("culturalSum",culturalSum);
			tempMap.put("doubleRequqireSum",doubleRequqireSum);
			tempMap.put("doubleSum",doubleSum);

			double totalPercentage = Math.round(  (((double)totalSum/total*100)*100) / 100.0  );
			if(totalPercentage>100)
				totalPercentage = 100;
			tempMap.put("totalPercentage",totalPercentage);
			tempMap.put("total",total );

			double majorRequirePercentage = Math.round(  (((double)majorRequqireSum/totalrequireMajor*100)*100) / 100.0  );
			if(majorRequirePercentage>100)
				majorRequirePercentage = 100;
			tempMap.put("majorRequirePercentage",majorRequirePercentage);
			tempMap.put("totalrequireMajor",totalrequireMajor );

			double majorPercentage = Math.round(  (((double)majorSum/totalMajor*100)*100) / 100.0  );
			if(majorPercentage>100)
				majorPercentage = 100;
			tempMap.put("majorPercentage",majorPercentage);
			tempMap.put("totalMajor",totalMajor );

			double culturalPercentage = Math.round(  (((double)culturalSum/totalCultural*100) *100 ) / 100.0  );
			if(culturalPercentage>100)
				culturalPercentage = 100;
			tempMap.put("culturalPercentage",culturalPercentage);
			tempMap.put("totalCultural",totalCultural );

			double doubleRequirePercentage = Math.round(  (((double)doubleRequqireSum/totalrequireDouble*100) *100 ) / 100.0  );
			if(doubleRequirePercentage>100)
				doubleRequirePercentage = 100;
			tempMap.put("doubleRequirePercentage",doubleRequirePercentage);
			tempMap.put("totalrequireDouble",totalrequireDouble );

			double doublePercentage = Math.round(  (((double)doubleSum/totalDouble*100) *100 ) / 100.0  );
			if(doublePercentage>100)
				doublePercentage = 100;
			tempMap.put("doublePercentage",doublePercentage);
			tempMap.put("totalDouble",totalDouble );
		}


		/*for(int i=0 ; i<culturalList.size() ; i++) {
			System.out.println(culturalList.get(i).getYear()+" "+culturalList.get(i).getSemester()+" "+
					culturalList.get(i).getCourseId()+" "+culturalList.get(i).getSubjectName());
		}*/


		return tempMap;
	}

	public List<List<SubjectColor>> getNameList(String year, Student student, List<MyCourseRecord> majorList) {

		String tableName = null;
		String code = null;

		//전공 기초, 전공 심화일 경우
		if(student.getCourse().equals(courseA) || student.getCourse().equals(courseB)) {
			tableName = studentmapper.getTableName(majorCondition0, student.getDepartmentId(), year);
			code = studentmapper.getCode(majorCondition0, student.getDepartmentId(), year);
		}
		else if(student.getCourse().equals(courseC)) { //내가 타 과를 복전할 경우, 원래 전공의 전필 과목을 가져옴 ->
			tableName = studentmapper.getTableName(majorCondition1, student.getDepartmentId(), year);
			code = studentmapper.getCode(majorCondition1, student.getDepartmentId(), year);
		}
		else if(student.getCourse().equals(courseD)) {
			tableName = studentmapper.getTableName(majorCondition2, student.getDepartmentId(), year);
			code = studentmapper.getCode(majorCondition2, student.getDepartmentId(), year);
		}

		MajorRequire temp = studentmapper.getMajorRequire(tableName, code, student.getCourse());
		List<List<String>> list = new ArrayList<>();

		//문자열 -> 배열 -> 리스트
		if(temp.getFirstSemester() != null && !(temp.getFirstSemester().equals("")))
			list.add(new ArrayList<>(Arrays.asList(temp.getFirstSemester().split(","))));
		if(temp.getSecondSemester() != null && !temp.getSecondSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSecondSemester().split(","))));
		if(temp.getThirdSemester() != null && !temp.getThirdSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getThirdSemester().split(","))));
		if(temp.getFourthSemester() != null && !temp.getFourthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getFourthSemester().split(","))));
		if(temp.getFifthSemester() != null && !temp.getFifthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getFifthSemester().split(","))));
		if(temp.getSixthSemester() != null && !temp.getSixthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSixthSemester().split(","))));
		if(temp.getSeventhSemester() != null && !temp.getSeventhSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSeventhSemester().split(","))));
		//if(temp.getEighthSemester() != null || !(temp.getEighthSemester().equals("")))
		if(temp.getEighthSemester() != null && !(temp.getEighthSemester().equals("")))
			list.add(new ArrayList<>(Arrays.asList(temp.getEighthSemester().split(","))));

		List<List<SubjectColor>> nameList = new ArrayList<>();

		//리스트를 가지고 과목 이름으로 가져오기
		for(int i=0 ; i<list.size() ; i++) {
			List<SubjectColor> tempList =  new ArrayList<>();
			for(int k=0 ; k<list.get(i).size() ; k++) {
				SubjectColor tempColor = new SubjectColor();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("courseId", list.get(i).get(k));
				tempMap.put("year", year);
				String tempName = studentmapper.getMajorName(tempMap);
				if(tempName==null) {
					tempName = studentmapper.getMajorName2(tempMap);
				}
				tempColor.setName(tempName);
				tempColor.setCourseId(list.get(i).get(k));
				tempColor.setCheck(false);
				tempList.add(tempColor);
				//System.out.println(tempColor.getCourseId() +"  "+tempColor.getName());
			}
			nameList.add(tempList);
		}

		//수강한 과목인지 채크값 넣어주기
		for(int i=0 ; i<nameList.size() ; i++) {
			for(int k=0 ; k<nameList.get(i).size() ; k++) {
				for(int n=0 ; n<majorList.size() ; n++) {
					if(nameList.get(i).get(k).getCourseId().equals(majorList.get(n).getCourseId())) {
						if( !(majorList.get(n).getGrade().equals("NP")) && !(majorList.get(n).getGrade().equals("F"))){
							nameList.get(i).get(k).setCheck(true);
							break;
						}
					}
				}
			}
		}

		/*
		for(int i=0 ; i<nameList.size() ; i++) {
			for(int k=0 ; k<nameList.get(i).size() ; k++) {
				System.out.println(nameList.get(i).get(k).getCourseId()+" "+ nameList.get(i).get(k).getName() +" "+ nameList.get(i).get(k).isCheck());
			}
		}
		 */

		return nameList;

	}
	/*
	public Map<String, Object> getDoubleMajorRecord(String year, int departId, int anotherMajorDepart, String studentId) {

		List<MyCourseRecord> list = studentmapper.getStudentRecord(studentId);
		List<MyCourseRecord> majorList = new ArrayList<>(); // 전공 저장 리스트
		List<MyCourseRecord> culturalList = new ArrayList<>(); //교양 저장 리스트
		List<MyCourseRecord> doubleList = new ArrayList<>(); //복전 또는 부전공 저장 리스트
		List<MyCourseRecord> majorReqquireList = new ArrayList<>(); //전필 저장 리스트
		int totalSum=0; //전체 학점 저장
		int majorSum=0; //전공 학점 저장
		int culturalSum=0; //교양 학점 저장
		int doubleSum=0; //복전 또는 부전공 학점 저장
		int majorRequqireSum = 0; //전필 수강 학점

		//name 채워주기
		for(int i=0 ; i<list.size() ; i++) {

			//논패스나 F가 아닌 경우게 합산 저장
			if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
				totalSum = totalSum + list.get(i).getCredits();
			else
				list.get(i).setCredits(0);

			//전필 과목일 경우 majorReqquireList에 저장
			String tempName = studentmapper.getMajorRequireCourseName(list.get(i).getCourseId(),
					list.get(i).getYear(), list.get(i).getSemester(), departId);
			if(tempName != null) {
				majorReqquireList.add(list.get(i));
				if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
					majorRequqireSum = majorRequqireSum + list.get(i).getCredits();
			}

			//전공 과목일 경우 이름 저장 + majorList에 추가
			String temp = studentmapper.getMajorCourseName2(list.get(i).getCourseId(), list.get(i).getYear(), list.get(i).getSemester(), departId);
			if(temp != null) {
				list.get(i).setName(temp);
				majorList.add(list.get(i));
				//논패스나 F가 아닌 경우게 합산 저장
				if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
					majorSum = majorSum + list.get(i).getCredits();
			}
			else if(temp == null || temp.equals("")) {
				temp = studentmapper.getMajorCourseName2(list.get(i).getCourseId(), list.get(i).getYear(), list.get(i).getSemester(), anotherMajorDepart);
				//복전 또는 부전공 과목일 경우
				if(temp != null) {
					//	System.out.println("복전입니다.");
					list.get(i).setName(temp);
					doubleList.add(list.get(i));
					if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
						doubleSum = doubleSum + list.get(i).getCredits();
				}
				//교양 또는 다른 과의 과목일 경우
				else {
					temp = studentmapper.getCulturalCourseName(list.get(i) );
					if(temp.contains("채플"))
						list.get(i).setName("채플");
					else
						list.get(i).setName(temp);
					//exception이 1일 경우 -> 이수변경 신청해서 허가 난 경우 전공으로 계산해야함
					if(list.get(i).getException()==1) {
						majorList.add(list.get(i));
						//논패스나 F가 아닌 경우게 합산 저장
						if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
							majorSum = majorSum + list.get(i).getCredits();
					}else {
						culturalList.add(list.get(i));
						//논패스나 F가 아닌 경우게 합산 저장
						if( !(list.get(i).getGrade().equals("NP")) && !(list.get(i).getGrade().equals("F")))
							culturalSum = culturalSum + list.get(i).getCredits();
					}
				}
			}

			//System.out.println("year : "+list.get(i).getYear()+" / 학기 : "+list.get(i).getSemester()+" / courseId : "+
			//		list.get(i).getCourseId()+" / 이름 : "+list.get(i).getName()+" / 평점 : "+list.get(i).getGrade()+" / 학점 : "+list.get(i).getCredits());
		}
		System.out.println("doubleSum="+doubleSum);

		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("AllList", list);
		tempMap.put("CulturalList", culturalList);
		tempMap.put("MajorList", majorList);
		tempMap.put("DoubleList", doubleList);
		tempMap.put("MajorReqquireList",majorReqquireList); //

		//Math.round((((double) answerCount/sum*100) * 100) / 100.0 )

		tempMap.put("totalSum",totalSum);
		tempMap.put("majorSum",majorSum);
		tempMap.put("culturalSum",culturalSum);
		tempMap.put("doubleSum", doubleSum);
		tempMap.put("majorRequqireSum",majorRequqireSum);

		//수료 학점 가져오기

		//departId + majorCondition1 으로 주전공 테이블 이름이랑 코드 가져오기
		String majorTableName = studentmapper.getTableName(majorCondition1, departId, year);
		String majorCode = studentmapper.getCode(majorCondition1, departId, year);

		//anotherMajorDepart + majorCondition3 으로 복전 또는 부전공 테이블 이름이랑 코드 가져오기

		String doubleTableName = studentmapper.getTableName(majorCondition3, anotherMajorDepart, year);
		String doubleCode = studentmapper.getCode(majorCondition3, anotherMajorDepart, year);

		//System.out.println("주전공 ="+majorTableName+" "+majorCode);
		//System.out.println("복수전공 ="+doubleTableName+" "+doubleCode);

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("tableName", majorTableName);
		map1.put("code", majorCode);
		map1.put("course", majorCondition1);

		MajorRequire major = studentmapper.getMajorRequire(map1);
		int total = major.getTotal();
		int totalMajor = major.getTotalMajor();
		int totalCultural = major.getTotalCultural();
		int totalrequireMajor = major.getRequireMajor(); //전필 요구 학점

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("tableName", doubleTableName);
		map2.put("code", doubleCode);
		map2.put("course", majorCondition3);

		MajorRequire doubleMajor = studentmapper.getMajorRequire(map2);
		int totalDouble = doubleMajor.getTotalMajor();

		//System.out.println("전공 요구 학점 :"+totalMajor);
		//System.out.println("전필 요구 학점 :"+totalrequireMajor);
		//System.out.println("교양 요구 학점 :"+ totalCultural);
		//System.out.println("복전 요구 학점 :"+totalDouble);


		double totalPercentage = Math.round(  (((double)totalSum/total*100)*100) / 100.0  );
		if(totalPercentage>100)
			totalPercentage = 100;
		tempMap.put("totalPercentage",totalPercentage);
		tempMap.put("total",total );

		double majorRequirePercentage = Math.round(  (((double)majorRequqireSum/totalrequireMajor*100)*100) / 100.0  );
		if(majorRequirePercentage>100)
			majorRequirePercentage = 100;
		tempMap.put("majorRequirePercentage",majorRequirePercentage);
		tempMap.put("totalrequireMajor",totalrequireMajor );

		double majorPercentage = Math.round(  (((double)majorSum/totalMajor*100)*100) / 100.0  );
		if(majorPercentage>100)
			majorPercentage = 100;
		tempMap.put("majorPercentage",majorPercentage);
		tempMap.put("totalMajor",totalMajor );

		double culturalPercentage = Math.round(  (((double)culturalSum/totalCultural*100) *100 ) / 100.0  );
		if(culturalPercentage>100)
			culturalPercentage = 100;
		tempMap.put("culturalPercentage",culturalPercentage);
		tempMap.put("totalCultural",totalCultural );

		double doublePercentage = Math.round(  (((double)doubleSum/totalDouble*100) *100 ) / 100.0  );
		if(doublePercentage>100)
			doublePercentage = 100;
		tempMap.put("doublePercentage",doublePercentage);
		tempMap.put("totalDouble",totalDouble );

		//System.out.println(totalPercentage+" "+majorRequirePercentage+" "+majorPercentage+" "+culturalPercentage+" "+doublePercentage);
		return tempMap;
	}*/

	//타과를 복전 또는 부전공 경우 해당 과의 전필들을 가져온다.
	public List<List<SubjectColor>> getDoubleNameList(String year, Student student, List<MyCourseRecord> majorList) {

		//for(int i=0 ; i<majorList.size() ; i++) {
		//	System.out.println(majorList.get(i).getName() + "  " + majorList.get(i).getCourseId());
		//}
		String tableName;
		String code;
		MajorRequire temp;

		if(student.getCourse().equals(courseC)) { //복전일 경우
			tableName = studentmapper.getTableName(majorCondition3, student.getAnotherMajorDepart(), year);
			code = studentmapper.getCode(majorCondition3, student.getAnotherMajorDepart(), year);
			temp = studentmapper.getMajorRequire(tableName, code, majorCondition3);
		}else { //부전공일 경우
			tableName = studentmapper.getTableName(majorCondition4, student.getAnotherMajorDepart(), year);
			code = studentmapper.getCode(majorCondition4, student.getAnotherMajorDepart(), year);
			temp = studentmapper.getMajorRequire(tableName, code, majorCondition4);
		}

		List<List<String>> list = new ArrayList<>();

		//System.out.println(temp.getFirstSemester());
		//System.out.println(temp.getSecondSemester());
		//System.out.println(temp.getThirdSemester());

		//문자열 -> 배열 -> 리스트
		if(temp.getFirstSemester() != null && !temp.getFirstSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getFirstSemester().split(","))));
		if(temp.getSecondSemester() != null && !temp.getSecondSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSecondSemester().split(","))));
		if(temp.getThirdSemester() != null && !temp.getThirdSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getThirdSemester().split(","))));
		if(temp.getFourthSemester() != null && !temp.getFourthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getFourthSemester().split(","))));
		if(temp.getFifthSemester() != null && !temp.getFifthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getFifthSemester().split(","))));
		if(temp.getSixthSemester() != null && !temp.getSixthSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSixthSemester().split(","))));
		if(temp.getSeventhSemester() != null && !temp.getSeventhSemester().equals(""))
			list.add(new ArrayList<>(Arrays.asList(temp.getSeventhSemester().split(","))));
		//if(temp.getEighthSemester() != null || !(temp.getEighthSemester().equals("")))
		if(temp.getEighthSemester() != null && !(temp.getEighthSemester().equals("")))
			list.add(new ArrayList<>(Arrays.asList(temp.getEighthSemester().split(","))));

		List<List<SubjectColor>> nameList = new ArrayList<>();

		//System.out.println("-----------------");
		//System.out.println(list.get(0).get(0));
		//System.out.println(list.get(0).get(1));
		//System.out.println(list.get(1).get(0));


		//리스트를 가지고 과목 이름으로 가져오기
		for(int i=0 ; i<list.size() ; i++) {
			List<SubjectColor> tempList =  new ArrayList<>();
			for(int k=0 ; k<list.get(i).size() ; k++) {
				SubjectColor tempColor = new SubjectColor();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("courseId", list.get(i).get(k));
				tempMap.put("year", year);

				String tempName = studentmapper.getMajorName(tempMap);
				if(tempName==null) {
					tempName = studentmapper.getMajorName2(tempMap);
				}
				tempColor.setName(tempName);
				tempColor.setCourseId(list.get(i).get(k));
				tempColor.setCheck(false);
				tempList.add(tempColor);
				//System.out.println(tempColor.getName()+"  "+tempColor.isCheck());

			}
			nameList.add(tempList);
		}

		//System.out.println("---------------");
		//수강한 과목인지 채크값 넣어주기
		for(int i=0 ; i<nameList.size() ; i++) {
			//	System.out.println("a");
			for(int k=0 ; k<nameList.get(i).size() ; k++) {
				//System.out.println("b");
				for(int n=0 ; n<majorList.size() ; n++) {
					//System.out.println("c");
					//nameList.get(i).get(k).getName() -> C프로그래밍Ⅰ
					if(nameList.get(i).get(k).getCourseId().equals(majorList.get(n).getCourseId())) {
						if( !(majorList.get(n).getGrade().equals("NP")) && !(majorList.get(n).getGrade().equals("F"))){
							nameList.get(i).get(k).setCheck(true);
							break;
						}
					}
					//System.out.println(nameList.get(i).get(k).getName()+" "+nameList.get(i).get(k).isCheck());
				}
			}
		}

		/*
		for(int i=0 ; i<nameList.size() ; i++) {
			for(int k=0 ; k<nameList.get(i).size() ; k++) {
				System.out.println(nameList.get(i).get(k).getCourseId()+" "+ nameList.get(i).get(k).getName() +" "+ nameList.get(i).get(k).isCheck());
			}
		}
		 */

		return nameList;

	}


	public Map<String, Object> getCulturalList(final String condition, List<MyCourseRecord> culturalList,
			String year, int departId, String studentId) {

		Map<String, Object> temp = new HashMap<>();
		String tableName = studentmapper.getCulturalTableName(condition, departId, year);
		String code = studentmapper.getCulturalCode(condition, departId, year);

		CulturalRequire culturalRequire = studentmapper.getCulturalRequire(tableName, code);

		/*System.out.println("교양 필수 과목 :"+culturalRequire.getRequireSubject()+" / 채플 이수 횟수 :"+culturalRequire.getChapel()+
				" / 사회봉사 이수 횟수 :"+culturalRequire.getVoluntary()+
				" / 사회봉사 과목 :"+culturalRequire.getVoluntarySubject()+" 총 교양 학점 :"+culturalRequire.getTotalCultural());

		System.out.println("추가적인 학과 교양이 있는가? ->"+culturalRequire.getAdditionalSubject());
		 */

		int chapelSum = 0;
		int volSum = 0;
		boolean checkRequire = false;
		boolean checkAdd = false;

		//사횝봉사는 디비에서 not NULL로 디폴트값 정해놔서 null일 수 없다
		List<String> voluntarySubject = Arrays.asList(culturalRequire.getVoluntarySubject().split(","));

		//교필 과목이 있을 경우
		List<String> requireSubject = new ArrayList<>();
		if(culturalRequire.getRequireSubject() != null) {
			checkRequire = true;
			requireSubject = Arrays.asList(culturalRequire.getRequireSubject().split(","));
		}
		//System.out.println(requireSubject.get(0));

		//학과 추가 교양 과목이 있을 경우
		List<String> additionSubject = new ArrayList<>();
		if(culturalRequire.getAdditionalSubject() != null) {
			checkAdd = true;
			additionSubject = Arrays.asList(culturalRequire.getAdditionalSubject().split(","));
		}
		//System.out.println("---");
		//System.out.println(additionSubject.get(0));

		List<SubjectColor> requireList =  new ArrayList<>();
		List<SubjectColor> additionList =  new ArrayList<>();

		for(int i=0 ; i<culturalList.size() ; i++) {

			//내가 들은 과목이 채플일 경우
			if(culturalList.get(i).getSubjectName().contains("채플"))
				chapelSum++;
			//내가 들은 과목이 채플이 아닌 경우->사회봉사 또는 다른 교양
			else {

				//사회봉사일 경우 카운트 해주기
				if(culturalRequire.getVoluntary() != volSum) {
					for(int k=0 ; k<voluntarySubject.size() ; k++) {
						if(culturalList.get(i).getCourseId().equals(voluntarySubject.get(k))) {
							volSum++;
							break;
						}
					}
				}

				//채플도 사회봉사도 아닌 교양 과목인 경우 필수 교양, 학과 지정 교양이면 수강했나 확인해서 색 지정하기
				if(checkRequire) { //교필과목이 존재할 경우
					SubjectColor sub = new SubjectColor();
					sub.setCheck(false);

					//논패스 그리고 f가 아닐 경우
					if( !(culturalList.get(i).getGrade().equals("NP")) && !(culturalList.get(i).getGrade().equals("F"))) {
						for(int j=0 ; j<requireSubject.size() ; j++) {
							if(culturalList.get(i).getCourseId().equals( requireSubject.get(j) )) {
								sub.setCheck(true);
								sub.setCourseId(culturalList.get(i).getCourseId());
								sub.setName(culturalList.get(i).getSubjectName());
								requireList.add(sub);
							}
						}
					}
				}
				if(checkAdd) { //학과 지정 교양
					SubjectColor sub = new SubjectColor();
					sub.setCheck(false);
					//논패스 그리고 f가 아닐 경우
					if( !(culturalList.get(i).getGrade().equals("NP")) && !(culturalList.get(i).getGrade().equals("F"))) {
						for(int j=0 ; j<additionSubject.size() ; j++) {
							if(culturalList.get(i).getCourseId().equals( additionSubject.get(j) )) {
								sub.setCheck(true);
								sub.setCourseId(culturalList.get(i).getCourseId());
								sub.setName(culturalList.get(i).getSubjectName());
								additionList.add(sub);
							}
						}
					}
				}
			}
		}

		if(checkRequire) { //교필과목이 존재할 경우
			System.out.println("교필");
			/*for(int i=0 ; i<requireList.size() ; i++) {
				System.out.println("이름 :"+requireList.get(i).getName()+" / 코드 :"+requireList.get(i).getCourseId()+
						" / 이수여부 :"+requireList.get(i).isCheck());
			}*/
			temp.put("RequireList", requireList); //교필 과목 리스트와 수강 여부
		}
		if(checkAdd) { //학과 지정 교양
			System.out.println("학과지정 교양");
			/*for(int i=0 ; i<additionList.size() ; i++) {
				System.out.println("이름 :"+additionList.get(i).getName()+" / 코드 :"+additionList.get(i).getCourseId()+
						" / 이수여부 :"+additionList.get(i).isCheck());
			}*/
			temp.put("AdditionList", additionList); //학과 지정 교양 과목 리스트와 수강 여부
		}


		//System.out.println("내가 들은 채플 수 :"+chapelSum+" / 내가 들은 사회봉사 수 :"+volSum);
		temp.put("Chapel", culturalRequire.getChapel()); //요구되는 채플 횟수
		temp.put("Voluntary", culturalRequire.getVoluntary()); //요구되는 사회봉사 횟수
		temp.put("ChapelSum", chapelSum); //내가 들은 채플 횟수
		temp.put("VoluntarySum", volSum); //내가 들은 사회봉사 횟수

		return temp;
	}

	public List<MyCourseRecord> getAllMyRecord(String studentId){
		return studentmapper.getStudentRecordPass(studentId);
	}

	public boolean getRecordId(int recordId) {

		Integer num = studentmapper.getRecordId(recordId);
		if(num==null)
			return false;
		return true;
	}

	public boolean setCategotyChange(CategoryChange categoryChange) {

		int num = studentmapper.setCategotyChange(categoryChange);
		if(num>0)
			return true;
		return false;
	}

	public List<CategoryChange> getStatus0(String studentId){
		return studentmapper.getStatus0(studentId);
	}

	public List<CategoryChange> getStatus1(String studentId){
		return studentmapper.getStatus1(studentId);
	}

	public List<CategoryChange> getStatus2(String studentId){
		return studentmapper.getStatus2(studentId);

	}
}
