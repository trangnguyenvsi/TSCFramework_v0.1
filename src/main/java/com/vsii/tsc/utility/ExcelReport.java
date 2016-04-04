package com.vsii.tsc.utility;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.vsii.tsc.model.TestCase;

public class ExcelReport {
	/*
	 * Write results to excel
	 */
	public static void writeTestResults(List<TestCase> tcTempleTable) throws IOException {
		String cellColor = "";
		tcTempleTable = TCTempleTable.getTCTemplateTable(ExtentReport.categoryName);
		int rowNum = Integer.parseInt(TestBase.p.getProperty("resultRow"));
		int resultIDCol = Integer.parseInt(TestBase.p.getProperty("resultIDCol"));
		int resultDesCol = Integer.parseInt(TestBase.p.getProperty("resultDesCol"));
		int resultPreCol = Integer.parseInt(TestBase.p.getProperty("resultPreCol"));
		int resultStepCol = Integer.parseInt(TestBase.p.getProperty("resultStepCol"));
		int resultExptCol = Integer.parseInt(TestBase.p.getProperty("resultExptCol"));
		int resultTestCol = Integer.parseInt(TestBase.p.getProperty("resultTestCol"));
		
			// Change cell color
			/* Get access to XSSFCellStyle */
			XSSFCellStyle myStyle = ExcelHandle.workbook.createCellStyle();
			/*
			 * We will now specify a background cell color
		     */

			myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			myStyle.setBorderTop((short)1);
			myStyle.setBorderBottom((short)1);
			myStyle.setBorderLeft((short)1);
			myStyle.setBorderRight((short)1);
			
			for(TestCase tc:tcTempleTable)

			if (testResult.equals("Passed")) {
				myStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			} else if (testResult.endsWith("Failed")) {
				myStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			}

			ExcelHandle.sheet.getRow(rowNum).getCell(newColNum).setCellStyle(myStyle);
			ExcelHandle.sheet.getRow(rowNum).getCell(newColNum).setCellValue(testResult);
									
								
								
		}
	}


}
