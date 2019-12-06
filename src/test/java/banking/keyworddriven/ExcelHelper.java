package banking.keyworddriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	
	Workbook book;
	Sheet sheet;
	
	private String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir")+File.separator+folderName+File.separator+fileName;
	}
	
	// open excel document
	public void openExcel(String folderName, String fileName, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(getFilePath(folderName, fileName));
			if(fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			}else {
				book = new XSSFWorkbook(fis);
			}
			sheet = book.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// number of rows
	public int rowCount() {
		return sheet.getLastRowNum();
	}
	
	// number of columns
	public int columnCount() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	// read a cell value
	public String readData(int rnum, int cnum) {
		String data = "";
		Cell cell = sheet.getRow(rnum).getCell(cnum);
		try {
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				double d = cell.getNumericCellValue();
				int i = (int) d;
				data = Integer.toString(i);
			default:
				break;
			}
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
		return data;
	}
	
	
	// close the excel
	public void closeExcel() {
		try {
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ExcelHelper obj = new ExcelHelper();
		obj.openExcel("resources", "input.xlsx", "teststeps");
		int nor = obj.rowCount();
		int noc = obj.columnCount();
		for(int r = 1;r <= nor; r++) {
			for(int c = 0; c< noc; c++) {
				System.out.print(obj.readData(r, c)+"\t");
			}
			System.out.println();
		}
		
		obj.closeExcel();
	}

}
