package egovframework.breeze.common;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import egovframework.golf.service.EventVO;

public class ExcelDownView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> map= (Map<String, Object>) model.get("categoryMap");
		String excelName = "";
		Sheet worksheet = null;
		Row row = null;
		
		excelName = URLEncoder.encode("applyList", "UTF-8");
		
		worksheet = workbook.createSheet(excelName + " WorkSheet");
		
		for (int i=0; i<=9; i++){ 
			worksheet.autoSizeColumn(i);
			worksheet.setColumnWidth(i, (worksheet.getColumnWidth(i))+1000 );
		}

		int j = 0;
		
		row = worksheet.createRow(0);
		row.createCell(j++).setCellValue("번호");
		row.createCell(j++).setCellValue("사용일");
		row.createCell(j++).setCellValue("티업시간");
		row.createCell(j++).setCellValue("인원");
		row.createCell(j++).setCellValue("신청인");
		row.createCell(j++).setCellValue("연락처");
		row.createCell(j++).setCellValue("숙소");
		row.createCell(j++).setCellValue("상태");
		row.createCell(j++).setCellValue("예약신청일");

		List<EventVO> list = (List<EventVO>) model.get("applyList");
		for(int i = 1; i < list.size()+1; i++) {
			row = worksheet.createRow(i);
			int k = 0;
			row.createCell(k++).setCellValue(i);
			row.createCell(k++).setCellValue(list.get(i-1).getEventDate());
			if(list.get(i-1).getAmpm().equals("AM")) {
				row.createCell(k++).setCellValue("오전");
			}else {
				row.createCell(k++).setCellValue("오후");
			}
			row.createCell(k++).setCellValue(list.get(i-1).getAdultNum());
			row.createCell(k++).setCellValue(list.get(i-1).getApplicant());
			row.createCell(k++).setCellValue(list.get(i-1).getPhone());
			row.createCell(k++).setCellValue(list.get(i-1).getAddress());
			if(list.get(i-1).getStatusFlag().equals("Y")) {
				row.createCell(k++).setCellValue("예약확정");
			}else if(list.get(i-1).getStatusFlag().equals("N")) {
				row.createCell(k++).setCellValue("취소");
			}else {
				row.createCell(k++).setCellValue("승인대기");
			}
			row.createCell(k++).setCellValue(list.get(i-1).getRegDate());
		}
		
		String currentDate = (String) model.get("currentDate");
		/* 엑셀 excel */
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="+ excelName + "_" + currentDate.replace("-", "_") + ".xlsx");
	}
}
