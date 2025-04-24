package com.daw.contafin.service;

import java.util.List;


import com.daw.contafin.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class ExcelService {

	@Autowired
	UserService userService;
	
	
	public SXSSFWorkbook generateExcel() {
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		List<UserDto> list = userService.getUsers();
		SXSSFSheet sheet = workbook.createSheet("User List");

		SXSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("NAME");
		header.createCell(1).setCellValue("EMAIL");
		header.createCell(2).setCellValue("LEVEL");
		header.createCell(3).setCellValue("POINTS");
		header.createCell(4).setCellValue("DAILY GOAL");
		header.createCell(5).setCellValue("STREAK");
		header.createCell(6).setCellValue("FLUENCY");
		header.createCell(7).setCellValue("EXPERIENCE");
		header.createCell(8).setCellValue("LAST CONNECTION");
		header.createCell(9).setCellValue("LAST UNIT");
		header.createCell(10).setCellValue("LAST LESSON");


		int rowNum = 1;

		for (UserDto user : list) {
			SXSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getName());
			row.createCell(1).setCellValue(user.getEmail());
			row.createCell(2).setCellValue(user.getLevel());
			row.createCell(3).setCellValue(user.getPoints());
			row.createCell(4).setCellValue(user.getDailyGoal());
			row.createCell(5).setCellValue(user.getStreak());
			row.createCell(6).setCellValue(user.getFluency());
			row.createCell(7).setCellValue(user.getExp());
			row.createCell(8).setCellValue(user.getLastConnection());
			row.createCell(9).setCellValue(user.getLastUnit());
			row.createCell(10).setCellValue(user.getLastLesson());

		}

		return workbook;
	}

}
