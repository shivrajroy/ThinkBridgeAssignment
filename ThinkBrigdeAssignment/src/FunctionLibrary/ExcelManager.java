package FunctionLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelManager {

	
	/**
	 * This function reads the Excel file By row
	 * @param XLS_FILE_PATH Path of the suit file
	 * @param sSheetName Name of the sheet to be read
	 * @param iRow Total no of rows
	 * @return Map
	 * @throws IOException 
	 */
	
	public Map<String,String> readExcelByRow(String XLS_FILE_PATH , String sSheetName , int iRow) throws IOException {

		
		InputStream minputStreamReadRow = null ;
		HSSFWorkbook mhssfwrkbokWorkbook;
		HSSFRow mhssfrowRow = null;
		HSSFSheet msheetSheet ; 
		Iterator mitrRows,mitrCells;	
		int noOfRows=0;
		Set set = null ;

				
		
		//@SuppressWarnings("unused")
		List<Map> data = new ArrayList<Map>();
		Map<String,String> hm = null;


		//------------Declare Excel Sheet Variables----------------// 
		minputStreamReadRow = new FileInputStream(XLS_FILE_PATH);
		mhssfwrkbokWorkbook = new HSSFWorkbook(minputStreamReadRow);
		mhssfrowRow = null;
		msheetSheet = mhssfwrkbokWorkbook.getSheet(sSheetName);
		mitrRows = msheetSheet.rowIterator();
		noOfRows = msheetSheet.getPhysicalNumberOfRows();
		;
		set = null ;
		int i = 1;

		//------------Count total no of scripts in a Module----------------// 
		for (; (i < noOfRows) && (mitrRows.hasNext()); i++) {
			if( i == iRow ) {
				break;
			}
		}

		hm = new HashMap<String,String>(); 
		mhssfrowRow = (HSSFRow) mitrRows.next();
		mitrCells = mhssfrowRow.cellIterator();
		int j=0 ;
		for( ;mitrCells.hasNext() ; j++) {
			mitrCells.next();
			hm.put( getValue(msheetSheet.getRow(0).getCell(j)) , getValue(msheetSheet.getRow(iRow).getCell(j , Row.CREATE_NULL_AS_BLANK)));
		}

		if (minputStreamReadRow != null) 
		{
			minputStreamReadRow.close();
			minputStreamReadRow = null;
		}
		minputStreamReadRow = null;


		//------------Return from function----------------//
		return(hm);
	}
	
	/**
	 * This is Private function reads the value in cell of excel file
	 * @param cell The cell's value to be read
	 * @return String
	 */
	//------------getValue() private function----------------//
		//@SuppressWarnings("deprecation")
		private String getValue( Cell cell){
			String value = "";

			//------------Get the value of cell into the 'value' string variable----------------//
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
				break;

			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;

			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;

			case HSSFCell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;

			default:
				break;
			}
			//------------Return from function----------------//
			return value;
		}

}
