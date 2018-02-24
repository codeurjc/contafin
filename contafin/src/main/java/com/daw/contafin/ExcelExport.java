package com.daw.contafin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.daw.contafin.user.User;


public class ExcelExport extends AbstractXlsView {

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-disposition", "attachment; filename=\"user_list.xls\"");

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) model.get("userList");	
		Sheet sheet = workbook.createSheet("User List");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("NAME");
		header.createCell(1).setCellValue("EMAIL");
		header.createCell(2).setCellValue("LEVEL");
		header.createCell(3).setCellValue("POINTS");
		header.createCell(4).setCellValue("DAILY GOAL");
		header.createCell(5).setCellValue("STREAK");
		header.createCell(6).setCellValue("FLUENCY");
		header.createCell(7).setCellValue("EXPERIENCE");
		header.createCell(8).setCellValue("LAST CONNECTION");

		int rowNum = 1;

		for(User user : list){
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getName());
			row.createCell(1).setCellValue(user.getEmail());
			row.createCell(2).setCellValue(user.getLevel());
			row.createCell(3).setCellValue(user.getPoints());
			row.createCell(4).setCellValue(user.getDailyGoal());
			row.createCell(5).setCellValue(user.getStreak());
			row.createCell(6).setCellValue(user.getFluency());
			row.createCell(7).setCellValue(user.getExp());
			row.createCell(8).setCellValue(user.getExp());

		}
	}

}
