package com.vsii.tsc.utility;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vsii.tsc.model.TestCase;

public class ExcelHandle {

	static FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static Cell cell;
	
	// Use for writeTestResults() method
	public static ArrayList<String> tcIDList;
	public static ArrayList<String> tcDescList;
	public static ArrayList<String> tcPreconList;
	public static ArrayList<String> tcStepList;
	public static ArrayList<String> tcExptList;
	public static HashMap<String, String> testcaseList;

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * Read data from table in excel file
	 */
	@SuppressWarnings("resource")
	public static String[][] getTable(String filePath, String sheetName, String tableName) {

		FileInputStream fileTable = null;
		XSSFWorkbook workbookTable = null;
		XSSFSheet sheetTable;
		XSSFRow rowTable;
		Cell cellTable;

		// Use for getTable() method
		String testData[][];

		String tagName = null;
		List<Cell> tagList = new ArrayList<Cell>();
		int startRow = 0;
		int startCol = 0;
		int endRow = 0;
		int endCol = 0;

		// Open file
		try {
			fileTable = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Define workbook
		try {
			workbookTable = new XSSFWorkbook(fileTable);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Define sheet
		sheetTable = workbookTable.getSheet(sheetName);

		// Iterate through each row
		Iterator<Row> rowIterator = sheetTable.iterator();
		while (rowIterator.hasNext()) {
			rowTable = (XSSFRow) rowIterator.next();
			Iterator<Cell> cellIterator = rowTable.iterator();
			while (cellIterator.hasNext()) {
				cellTable = cellIterator.next();
				// Check the cell type and format
				switch (cellTable.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					tagName = cellTable.getStringCellValue();
					// case Cell.CELL_TYPE_NUMERIC:
					// = String.valueOf(cell.getNumericCellValue());
				}

				if (tagName.equalsIgnoreCase(tableName)) {
					tagList.add(cellTable);
				}
			}
		}
		// Identify table
		if (tagList.size() == 2) {
			startRow = tagList.get(0).getRowIndex() + 1;
			System.out.println(startRow);
			startCol = tagList.get(0).getColumnIndex() + 1;
			System.out.println(startCol);
			endRow = tagList.get(1).getRowIndex() - 1;
			System.out.println(endRow);
			endCol = tagList.get(1).getColumnIndex() - 1;
			System.out.println(endCol);
		} else
			System.out.println("Wrong table");

		testData = new String[endRow - startRow + 1][endCol - startCol + 1];

		int x = 0;
		for (int i = startRow; i <= endRow; i++, x++) {
			int y = 0;
			for (int j = startCol; j <= endCol; j++, y++) {
				rowTable = sheetTable.getRow(i);
				testData[x][y] = rowTable.getCell(j).toString();
				System.out.println(testData[x][y]);
			}
		}
		return testData;
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	/*
	 * Get value list on the column
	 */
	@SuppressWarnings("resource")
	public static List<String> loadCellValueList(String sheetName, int tcIDCol) {

		List<String> cellValueList = new ArrayList<String>();
		int columnWanted;
		// define sheet
		sheet = workbook.getSheet(sheetName);

		// Col is Test Case ID's column
		columnWanted = tcIDCol;
		for (int r = 12; r < sheet.getLastRowNum(); r++) {
			Cell c = null;
			Row row1 = sheet.getRow(r);
			c = row1.getCell(columnWanted);
			if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
				// Nothing in the cell in this row, skip it
			} else
				cellValueList.add(c.getStringCellValue());
		}
		return cellValueList;

	}

	// -------------------------------------------------------------------------------------------------------------------------------
	/*
	 * Open excel file by filepath, sheet name, index of TC's ID, TC's Description and TC's steps
	 */
	public static void openExcelFile(String filePath, String sheetName, int tcIDCol, int tcDescCol,int tcPreCol, int tcStepCol, int tcExptCol)
			throws IOException {
		file = new FileInputStream(new File(filePath));
		workbook = new XSSFWorkbook(file);

		// create a new work sheet
		sheet = workbook.getSheet(sheetName);

		// list of test case name
		tcIDList = (ArrayList<String>) ExcelHandle.loadCellValueList(sheetName, tcIDCol);
		tcDescList = (ArrayList<String>) ExcelHandle.loadCellValueList(sheetName, tcDescCol);
		tcPreconList= (ArrayList<String>) ExcelHandle.loadCellValueList(sheetName, tcPreCol);
		tcStepList = (ArrayList<String>) ExcelHandle.loadCellValueList(sheetName, tcStepCol);
		tcExptList = (ArrayList<String>) ExcelHandle.loadCellValueList(sheetName, tcExptCol);
		file.close();
	}

	// -------------------------------------------------------------------------------------------------------------------------------
//	/*
//	 * Write results to excel
//	 */
//	public static void writeTestResults(List<TestCase> tcTempleTable) throws IOException {
//		String cellColor = "";
//		int rowNum = Integer.parseInt(TestBase.p.getProperty("resultRow"));
//		int resultIDCol = Integer.parseInt(TestBase.p.getProperty("resultIDCol"));
//		int resultDesCol = Integer.parseInt(TestBase.p.getProperty("resultDesCol"));
//		int resultPreCol = Integer.parseInt(TestBase.p.getProperty("resultPreCol"));
//		int resultStepCol = Integer.parseInt(TestBase.p.getProperty("resultStepCol"));
//		int resultExptCol = Integer.parseInt(TestBase.p.getProperty("resultExptCol"));
//		int resultTestCol = Integer.parseInt(TestBase.p.getProperty("resultTestCol"));
//		
//			// Change cell color
//			/* Get access to XSSFCellStyle */
//			XSSFCellStyle myStyle = workbook.createCellStyle();
//			/*
//			 * We will now specify a background cell color
//		     */
//
//			myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//			myStyle.setBorderTop((short)1);
//			myStyle.setBorderBottom((short)1);
//			myStyle.setBorderLeft((short)1);
//			myStyle.setBorderRight((short)1);
//			
//			for
//
//			if (testResult.equals("Passed")) {
//				myStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//			} else if (testResult.endsWith("Failed")) {
//				myStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
//			}
//
//			sheet.getRow(rowNum).getCell(newColNum).setCellStyle(myStyle);
//			sheet.getRow(rowNum).getCell(newColNum).setCellValue(testResult);
//									
//								
//								
//		}
//	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * Create excel file
	 */
	public static void createExcelFile(String fileName) {
		try {
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create hashmap
	 */
	public static HashMap<String, String> createHashMap(List<String> tcIDList, List<String> tcDescList) {
		testcaseList = new HashMap<String, String>();
		for (int i = 0; i < tcIDList.size(); i++) {
			testcaseList.put(tcIDList.get(i), tcDescList.get(i));
			System.out.println(tcIDList.get(i) + "|" + tcDescList.get(i));
		}
		return testcaseList;

	}
}
