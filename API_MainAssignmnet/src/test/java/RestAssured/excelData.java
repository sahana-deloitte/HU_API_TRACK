package RestAssured;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class excelData {
    public String getString(int row, int column) throws IOException {
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\API_MainAssignmnet\\Mindata.xlsx";
        FileInputStream fis = new FileInputStream(excelPath); //Reading the data from excel path
        XSSFWorkbook wb = new XSSFWorkbook(fis);  //to read/write data from xml path
        XSSFSheet sheet = wb.getSheetAt(0); //fetching data from sheet 0
        XSSFRow rows = null;
        XSSFCell cell = null;
        String input= null;  //input values
        rows = sheet.getRow(row);
        cell = rows.getCell(column);
        if(column == 2){
            int value =(int) cell.getNumericCellValue(); //extracting the input data from excel
            input=Integer.toString(value);   //converting from integer to string
            return input;
        }
        input = cell.getStringCellValue();
        return input;
    }
    public int getAge( int row, int column) throws IOException {
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\API_MainAssignmnet\\Mindata.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow rows = null;
        XSSFCell cell = null;
        rows = sheet.getRow(row);
        cell = rows.getCell(column);
        int age = (int) cell.getNumericCellValue();
        return age;
    }
    public void writeToken(Object ObjToken) throws IOException {
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\API_MainAssignmnet\\Mindata.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        row= sheet.getRow(1);
        cell = row.createCell(4);
        cell.setCellType(CellType.STRING);
        String token =(String)ObjToken;
        cell.setCellValue(token);
        FileOutputStream fos = new FileOutputStream(excelPath);
        wb.write(fos);
        fos.close();
    }
    public String getToken(int sht,int row,int col) throws IOException {
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\API_MainAssignmnet\\Mindata.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sht);
        XSSFRow rows = null;
        XSSFCell cell = null;
        String input= null;
        rows = sheet.getRow(row);
        cell = rows.getCell(col);
        input= cell.getStringCellValue();
        return input;
    }
}
