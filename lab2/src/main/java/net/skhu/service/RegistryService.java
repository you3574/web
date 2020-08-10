package net.skhu.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.Exception.ExcelBlankException;
import net.skhu.VO.Course;
import net.skhu.VO.MyCourseRecord;
import net.skhu.mapper.ExcelMapper;

@Service
public class RegistryService {

	private static final String A = "전공필수";
	private static final String B = "전공선택";
	private static final String C = "교양필수";
	private static final String D = "교양선택";
	private static final String a = "전필";
	private static final String b = "전선";
	private static final String c = "교필";
	private static final String d = "교선";
	private static final String E = "자연계 ";
	private static final String e = "사회계 디지털컨텐츠학과";
	private static final String F = "공학계 ";
	private static final String f = "공학계 컴퓨터공학과";
	private static final String G = "전공탐색";
	private static final String g = "전탐";

	@Autowired
	private ExcelMapper excelMapper;

	public List<MyCourseRecord> studentRecord(final InputStream excel, String studentId)
			throws Exception  {

		List<MyCourseRecord> list = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(excel); //엑셀을 읽는다
		XSSFSheet sheet = workbook.getSheetAt(0); //첫번째 시트를 읽는다
		int rowMax = sheet.getPhysicalNumberOfRows(); //행의 최대 값을 얻는다.
		for(int rowIndex = 2 ; rowIndex < rowMax ; rowIndex++ ) { //행을 돈다
			MyCourseRecord temp = new MyCourseRecord();
			XSSFRow row = sheet.getRow(rowIndex); //행 한 줄을 얻는다

			//엑셀 파일이 이상해서 이런식으로 행 끝났는지 판단해야함
			XSSFCell checkCell = row.getCell(1);
			if(checkCell == null)
				break;
			else if(checkCell.getCellType() == CellType.ERROR)
				break;
			else if(checkCell.getCellType() == CellType._NONE)
				break;
			else if(checkCell.getCellType() == CellType.BLANK)
				break;

			temp.setStudentId(studentId);

			for(int cellIndex=1 ;  cellIndex < 8  ; cellIndex++) { //열을 돈다
				XSSFCell cell = row.getCell(cellIndex); //셀 하나를 얻는다.

				if(cell.getCellType()==CellType.BLANK) //셀이 비어있으면 에러
					throw new ExcelBlankException("셀이 비어있습니다.");
				else if(cell.getCellType()== CellType._NONE) //셀 타입을 모르면 에러
					throw new Exception();
				else if(cell.getCellType()==CellType.ERROR) //셀에 오류가 있으면 에러
					throw new Exception();
				else { //셀이 비어있지도 않고 오류도 없을 경우
					if(cellIndex==1) { //년도
						switch (cell.getCellTypeEnum() ) {
						case STRING:
							temp.setYear(Integer.parseInt(cell.getStringCellValue()));
							break;
						case NUMERIC:
							temp.setYear((int)cell.getNumericCellValue());
							break;
						default:
							break;
						}
					}else if(cellIndex==2) { //학기
						temp.setSemester(cell.getStringCellValue());
					}else if(cellIndex==3) { //과목코드
						temp.setCourseId(cell.getStringCellValue());
					}else if(cellIndex==4) { //과목명
						temp.setSubjectName(cell.getStringCellValue());
					}else if(cellIndex==5) { //이수구분
						temp.setCategory(cell.getStringCellValue());
					}else if(cellIndex==6) { //학점
						switch (cell.getCellTypeEnum() ) {
						case STRING:
							temp.setCredits(Integer.parseInt(cell.getStringCellValue()));
							break;
						case NUMERIC:
							temp.setCredits((int)cell.getNumericCellValue());
							break;
						default:
							break;
						}
					}else if(cellIndex==7) { //성적등급
						temp.setGrade(cell.getStringCellValue());
					}else;
				}


			} //열 돌기 끝
			System.out.println(temp.getYear()+" "+temp.getSemester()+" "+temp.getCourseId()+" "+temp.getSubjectName()+
					" "+temp.getCategory()+" "+temp.getCredits()+" "+temp.getGrade());
			list.add(temp); //한 행을 리스트에 저장
		} //행 돌기 끝

		workbook.close();
		return list;
	}

	public List<MyCourseRecord> Bar_xlsx(final InputStream bar, List<MyCourseRecord> list, List<String> idList ) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook(bar);
		//int num = workbook.getActiveSheetIndex(); //70
		int num = workbook.getNumberOfSheets();


		for(int i=1 ; i<num ; i++) {

			XSSFSheet sheet = workbook.getSheetAt(i); //시트를 읽는다
			int rowMax = sheet.getPhysicalNumberOfRows(); //행의 최대 값을 얻는다.

			for(int rowIndex = 2 ; rowIndex < rowMax ; rowIndex++ ) { //행을 돈다
				MyCourseRecord temp = new MyCourseRecord();
				//if( (rowIndex==0) || (rowIndex==1) ) // 1행 2행 스킵
				//continue;

				XSSFRow row = sheet.getRow(rowIndex); //행을 얻는다

				//엑셀 파일이 이상해서 이런식으로 행 끝났는지 판단해야함
				XSSFCell checkCell = row.getCell(1);
				if(checkCell == null)
					break;
				if(checkCell.getCellType() == CellType.ERROR)
					break;
				if(checkCell.getCellType() == CellType._NONE)
					break;

				for(int cellIndex=1 ;  cellIndex < 8  ; cellIndex++) { //열을 돈다
					XSSFCell cell = row.getCell(cellIndex); //셀 하나를 얻는다.

					if(cellIndex==1) { //년도
						switch (cell.getCellTypeEnum() ) {
						case STRING:
							temp.setYear(Integer.parseInt(cell.getStringCellValue()));
							break;
						case NUMERIC:
							temp.setYear((int)cell.getNumericCellValue());
							break;
						default:
							break;
						}
					}else if(cellIndex==2) { //학기
						temp.setSemester(cell.getStringCellValue());
					}else if(cellIndex==3) { //과목코드
						temp.setCourseId(cell.getStringCellValue());
					}else if(cellIndex==6) { //학점
						switch (cell.getCellTypeEnum() ) {
						case STRING:
							temp.setCredits(Integer.parseInt(cell.getStringCellValue()));
							break;
						case NUMERIC:
							temp.setCredits((int)cell.getNumericCellValue());
							break;
						default:
							break;
						}
					}else if(cellIndex==7) { //성적등급
						temp.setGrade(cell.getStringCellValue());
					}else;
				} //열 돌기 끝

				temp.setStudentId(idList.get(i-1));
				list.add(temp);
			} //행 돌기 끝

		}//시트 돌기 끝
		/*
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println("학번="+list.get(i).getStudentId()+" / 년도="+list.get(i).getYear()+
					" / 학기"+list.get(i).getSemester()+" / 과목코드="+list.get(i).getCourseId()+
					" 학점="+list.get(i).getCredits()+" / 성적등급="+list.get(i).getGrade());
		}
		System.out.println(list.size());*/
		workbook.close();
		return list;
	}

	public boolean setMyRecord(List<MyCourseRecord> myList) {

		int num =  excelMapper.setMyRecord(myList);
		if(num>0)
			return true;
		else
			return false;
	}

	public List<Course> Course_xlsx(final InputStream allCourse) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook(allCourse);
		XSSFSheet sheet = workbook.getSheetAt(0); //시트를 읽는다
		int rowMax = sheet.getPhysicalNumberOfRows(); //행의 최대 값을 얻는다.
		List<Course> list = new ArrayList<>();

		for(int rowIndex = 5073 ; rowIndex < rowMax ; rowIndex++ ) { //행을 돈다
			Course temp = new Course();

			XSSFRow row = sheet.getRow(rowIndex); //행을 얻는다

			for(int cellIndex=0 ;  cellIndex < 9  ; cellIndex++) { //열을 돈다
				XSSFCell cell = row.getCell(cellIndex); //셀 하나를 얻는다.

				if(cellIndex==0) { //년도
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						temp.setYear(Integer.parseInt(cell.getStringCellValue()));
						break;
					case NUMERIC:
						temp.setYear((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}else if(cellIndex==1) { //학기
					temp.setSemester(cell.getStringCellValue());
				}else if(cellIndex==2) { //과목코드
					temp.setCourseId(cell.getStringCellValue());
				}else if(cellIndex==3){ //분반
					temp.setDivision((int)cell.getNumericCellValue());
				}else if(cellIndex==4){ //개설 소속
					String str1 = cell.getStringCellValue();
					if(str1.equals(E)) {
						temp.setDepartmentName(e);
					}else if(str1.equals(F)){
						temp.setDepartmentName(f);
					}else {
						temp.setDepartmentName(cell.getStringCellValue());
					}
				}else if(cellIndex==6){ //이름
					temp.setSubjectName(cell.getStringCellValue());
				}else if(cellIndex==7){ //이수구분
					String str = cell.getStringCellValue();
					if(str.equals(A)) {
						temp.setCategory(a);
					}else if(str.equals(B)){
						temp.setCategory(b);
					}else if(str.equals(C)) {
						temp.setCategory(c);
					}else if(str.equals(D)) {
						temp.setCategory(d);
					}else if(str.equals(G)) {
						temp.setCategory(g);
					}else {
						temp.setCategory(str);
					}
				}else if(cellIndex==8) { // 학점
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						String str = cell.getStringCellValue().substring(0, 1);
						temp.setCredits(Integer.parseInt(str));
						break;
					case NUMERIC:
						temp.setCredits((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}
			}//열을 돈다 끝

			//System.out.println(temp.getYear()+"\t"+temp.getSemester()+"\t"+temp.getCourseId()+"\t"+temp.getDivision()+"\t"+temp.getName()+"\t\t\t"+
			//		temp.getDepartmentName()+"\t\t\t"+temp.getCategory()+"\t"+temp.getCredits());
			list.add(temp);
		}//행을 돈다 끝

		workbook.close();

		return list;
	}

	public Boolean setAllCourse(List<Course> list) {

		int num = excelMapper.setAllCourse(list);
		if(num>0)
			return true;
		else
			return false;
	}
}
