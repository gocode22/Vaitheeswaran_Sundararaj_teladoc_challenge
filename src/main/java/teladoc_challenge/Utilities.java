package teladoc_challenge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

//This methods reads the excel based on the sheet name passed on and returns the values
	public static ArrayList<String> getRegisterData(String Name) {
		File file = new File(System.getProperty("user.dir") + "\\TestData\\RegisterTestData.xlsx");
		FileInputStream inputStream;
		ArrayList<String> registerData = new ArrayList<String>();

		try {
			inputStream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			XSSFSheet sheet = wb.getSheet(Name);

			for (Row myrow : sheet) {
				if (myrow.getRowNum() == 0)
					continue;
				String temp = "";
				for (Cell mycell : myrow) {

					try {
						temp = temp + mycell.getStringCellValue() + ";";
					} catch (IllegalStateException e) {
						temp = temp + mycell.getNumericCellValue() + ";";
					}

				}
				registerData.add(cleanseData(temp));
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return registerData;
	}

	public static String cleanseData(String s) {

		return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));

	}

}
