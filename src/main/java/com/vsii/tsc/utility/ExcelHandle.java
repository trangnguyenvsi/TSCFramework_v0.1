package com.vsii.tsc.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vsii.tsc.model.TCImageResults;
import com.vsii.tsc.model.TestCase;

public class ExcelHandle {

	static FileInputStream file;
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

	// Use for get TC Table template
	public static List<TestCase> tcList;
	public static TestCase testcase;

	// Use to copy file
	File source;
	File dest;

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
	public static List<String> loadCellValueList(XSSFWorkbook workbook, String sheetName, int tcIDCol) {

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
	 * Open excel file by filepath, sheet name, index of TC's ID, TC's
	 * Description and TC's steps
	 */
	public static void openExcelFile(XSSFWorkbook workbook, String filePath, String sheetName, int tcIDCol,
			int tcDescCol, int tcPreCol, int tcStepCol, int tcExptCol) throws IOException {
		file = new FileInputStream(new File(filePath));
		// workbook = new XSSFWorkbook(file);

		// create a new work sheet
		sheet = workbook.getSheet(sheetName);

		// list of test case name
		tcIDList = (ArrayList<String>) ExcelHandle.loadCellValueList(workbook, sheetName, tcIDCol);
		tcDescList = (ArrayList<String>) ExcelHandle.loadCellValueList(workbook, sheetName, tcDescCol);
		tcPreconList = (ArrayList<String>) ExcelHandle.loadCellValueList(workbook, sheetName, tcPreCol);
		tcStepList = (ArrayList<String>) ExcelHandle.loadCellValueList(workbook, sheetName, tcStepCol);
		tcExptList = (ArrayList<String>) ExcelHandle.loadCellValueList(workbook, sheetName, tcExptCol);
		file.close();
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * Create excel file
	 */
	public void createExcelFile(XSSFWorkbook workbook, String fileName) {
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

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * copy file
	 */
	public void copyFile(File source, File dest) {

		try {
			FileUtils.copyFile(source, dest);
			System.out.println("copy success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * Create TC Table Template
	 */
	public List<TestCase> getTCTemplateTable(XSSFWorkbook workbook, String sheetName, String fileName) {
		try {
			ExcelHandle.openExcelFile(workbook, fileName, sheetName,
					Integer.parseInt(TestBase.p.getProperty("tcIDCol")),
					Integer.parseInt(TestBase.p.getProperty("tcDescCol")),
					Integer.parseInt(TestBase.p.getProperty("tcPreCol")),
					Integer.parseInt(TestBase.p.getProperty("tcStepCol")),
					Integer.parseInt(TestBase.p.getProperty("tcExptCol")));

			tcList = new ArrayList<TestCase>();
			// Create test case List
			for (String id : ExcelHandle.tcIDList) {

				int index = ExcelHandle.tcIDList.indexOf(id);

				if (TestBase.tcImageResultsList.containsKey(id)) {
					testcase = new TestCase();
					testcase.setTcID(id);
					testcase.setTcDesc(ExcelHandle.tcDescList.get(index));
					testcase.setTcPrec(ExcelHandle.tcPreconList.get(index));
					testcase.setTcStep(ExcelHandle.tcStepList.get(index));
					testcase.setTcExpt(ExcelHandle.tcExptList.get(index));
					List<TCImageResults> imageResult = TestBase.tcImageResultsList.get(id);
					testcase.setTcImageResults(imageResult);
					tcList.add(testcase);
				}
			}

		} catch (NumberFormatException e1) {
			// print
			e1.printStackTrace();
		} catch (IOException e1) {
			// print
			e1.printStackTrace();
		}
		return tcList;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * Write results to excel
	 */
	@SuppressWarnings("deprecation")
	public void writeToExcel(XSSFWorkbook workbook, List<TestCase> tcTemple, String categoryName, File fileName) {
		sheet = workbook.getSheet(categoryName);
		/* Define test result file */
		int rowNum = Integer.parseInt(TestBase.p.getProperty("resultRow"));
		int resultIDCol = Integer.parseInt(TestBase.p.getProperty("resultIDCol"));
		int resultDesCol = Integer.parseInt(TestBase.p.getProperty("resultDesCol"));
		int resultPreCol = Integer.parseInt(TestBase.p.getProperty("resultPreCol"));
		int resultStepCol = Integer.parseInt(TestBase.p.getProperty("resultStepCol"));
		int resultExptCol = Integer.parseInt(TestBase.p.getProperty("resultExptCol"));
		int resultTestCol = Integer.parseInt(TestBase.p.getProperty("resultTestCol"));
		int rowTC = 0;
		int j = 0;
		for (TestCase tc : tcTemple) {
			System.out.println("tc size:" + tcTemple.size());
			System.out.println("size:" + tc.getTcImageResults().size());
			int i;
			rowTC = rowNum;	
			for (i = 0; i < tc.getTcImageResults().size(); i++) {

				ExcelHandle.sheet.getRow(rowNum).getCell(resultIDCol).setCellValue(tc.getTcID());
				ExcelHandle.sheet.getRow(rowNum).getCell(resultDesCol).setCellValue(tc.getTcDesc());
				ExcelHandle.sheet.getRow(rowNum).getCell(resultPreCol).setCellValue(tc.getTcPrec());
				ExcelHandle.sheet.getRow(rowNum).getCell(resultStepCol).setCellValue(tc.getTcStep());
				ExcelHandle.sheet.getRow(rowNum).getCell(resultExptCol).setCellValue(tc.getTcExpt());

				System.out.println("TC ID:" + tc.getTcID());
				System.out.println("TC Des:" + tc.getTcDesc());
				System.out.println("TC Pre:" + tc.getTcPrec());
				System.out.println("TC Step:" + tc.getTcStep());
				System.out.println("TC Expt:" + tc.getTcExpt());
				System.out.println("TC Results:" + tc.getTcImageResults().get(i).getTcResult());

				String result = tc.getTcImageResults().get(i).getTcResult().toString();
				/* Get access to XSSFCellStyle */
				XSSFCellStyle myStyle = workbook.createCellStyle();
				/*
				 * We will now specify a background cell color
				 */
				myStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				myStyle.setBorderTop((short) 1);
				myStyle.setBorderBottom((short) 1);
				myStyle.setBorderLeft((short) 1);
				myStyle.setBorderRight((short) 1);
				
				switch (result) {
				
				case "pass":
					myStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					break;
				case "fail":
					myStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
					break;
				default:
					myStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
					break;
				}
				sheet.getRow(rowNum).getCell(resultTestCol).setCellStyle(myStyle);
				sheet.getRow(rowNum).getCell(resultTestCol).setCellValue(result.toUpperCase() + "ED");
				rowNum++;
			}
			if (tc.getTcImageResults().size() > 1) 
			{
				j = rowTC + i - 1;
			}
			
		}
		/* Merge cell */
		CellRangeAddress mergeID = new CellRangeAddress(rowTC, j, resultIDCol, resultIDCol);
		CellRangeAddress mergeDes = new CellRangeAddress(rowTC, j, resultDesCol, resultDesCol);
		CellRangeAddress mergePre = new CellRangeAddress(rowTC, j, resultPreCol, resultPreCol);
		CellRangeAddress mergeStep = new CellRangeAddress(rowTC, j, resultStepCol, resultStepCol);
		CellRangeAddress mergeExpt = new CellRangeAddress(rowTC, j, resultExptCol, resultExptCol);

		sheet.addMergedRegion(mergeID);
		sheet.addMergedRegion(mergeDes);
		sheet.addMergedRegion(mergePre);
		sheet.addMergedRegion(mergeStep);
		sheet.addMergedRegion(mergeExpt);

	}

	/*
	 * Create hashmap
	 */
	// public static HashMap<String, String> createHashMap(List<String>
	// tcIDList, List<String> tcDescList) {
	// testcaseList = new HashMap<String, String>();
	// for (int i = 0; i < tcIDList.size(); i++) {
	// testcaseList.put(tcIDList.get(i), tcDescList.get(i));
	// System.out.println(tcIDList.get(i) + "|" + tcDescList.get(i));
	// }
	// return testcaseList;
	//
	// }
}
