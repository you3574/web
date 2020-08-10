package net.skhu.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.VO.MyCourse;
import net.skhu.VO.User;

import net.skhu.mapper.ExcelMapper;
@Service
public class ExcelService {

	@Autowired
	private ExcelMapper excelMapper;

	public List<MyCourse> excelToObject(InputStream input,User user) throws Exception {

		List<MyCourse> myCourses = new ArrayList<>();

		//업로드된 액셀 파일INPUT을 읽기 위한 Workbook 객체를 생성
		Workbook workbook = WorkbookFactory.create(input);

		//업로드된 액셀 파일의 첫번째 시트를 읽기 위한 Sheet 객체를 생성
		Sheet sheet = workbook.getSheetAt(0);

		for (int r = 1; r < sheet.getPhysicalNumberOfRows() ; ++r) {
			Row row = sheet.getRow(r);
			//현재 행의 첫번째 컬럼에 데이터가 없으면, 읽기 종료
			if (row.getCell(0) == null) break;

			int year = (int)row.getCell(0).getNumericCellValue();
			String semester = row.getCell(1).getStringCellValue();
			String courseId = row.getCell(2).getStringCellValue();
			String subjectName = row.getCell(3).getStringCellValue();
			String category = row.getCell(4).getStringCellValue();
			int credits = (int)row.getCell(5).getNumericCellValue();
			String grade = row.getCell(6).getStringCellValue();

			myCourses.add(
					new MyCourse(year, semester, courseId, subjectName, category, credits, grade, user.getId())
					);
		}
		workbook.close();
		return myCourses;
	}

	public void setMyCourses(List<MyCourse> myCourses) {

		excelMapper.setMyCourses(myCourses);

	}
	
	public List<MyCourse> getMyCourses(String userId) {

		return excelMapper.getMyCourses(userId);
	}


}
