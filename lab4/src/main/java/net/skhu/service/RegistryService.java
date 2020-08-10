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

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;

@Service
public class RegistryService {


	@Autowired
	private StudentMapper studentmapper;

	public List<Student> studentRecord(final InputStream excel)
			throws Exception  {

		List<Student> list = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(excel); //엑셀을 읽는다
		XSSFSheet sheet = workbook.getSheetAt(0); //첫번째 시트를 읽는다
		int rowMax = sheet.getPhysicalNumberOfRows(); //행의 최대 값을 얻는다.
		for(int rowIndex = 0 ; rowIndex < rowMax ; rowIndex++ ) { //행을 돈다
			Student temp = new Student();
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


			for(int cellIndex=0 ;  cellIndex < 4  ; cellIndex++) { //열을 돈다
				XSSFCell cell = row.getCell(cellIndex); //셀 하나를 얻는다.
				if(cellIndex==0) { //년도
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						temp.setStudentNumber(cell.getStringCellValue());
						break;
					case NUMERIC:
						temp.setStudentNumber((int)cell.getNumericCellValue()+"");
						break;
					default:
						break;
					}
				}else if(cellIndex==1) {
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						temp.setName(cell.getStringCellValue());
						break;
					case NUMERIC:
						temp.setName((int)cell.getNumericCellValue()+"");
						break;
					default:
						break;
					}
				}else if(cellIndex==2) {
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						temp.setDepartmentId(Integer.parseInt(cell.getStringCellValue()) );
						break;
					case NUMERIC:
						temp.setDepartmentId((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}else if(cellIndex==3) {
					switch (cell.getCellTypeEnum() ) {
					case STRING:
						temp.setYear(Integer.parseInt(cell.getStringCellValue()) );
						break;
					case NUMERIC:
						temp.setYear((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
				}else;
			} //열 돌기 끝
			System.out.println(temp.getStudentNumber()+" "+temp.getName()+" "+temp.getDepartmentId()+" "+temp.getYear());
			list.add(temp); //한 행을 리스트에 저장

		} //행 돌기 끝


		workbook.close();
		return list;
	}

}
