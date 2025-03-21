package RestAssured.RestAssuredPOM.utilities;


import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {

    private String filePath;
    private FileInputStream fileInputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    // Constructor to load the Excel file
    public ExcelUtility(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Excel file not found at: " + filePath);
        }
        fileInputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " not found in " + filePath);
        }
        fileInputStream.close();
    }

    // Get total row count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get total column count (assumes first row as reference)
    public int getColumnCount() {
        XSSFRow row = sheet.getRow(0);
        return (row == null) ? 0 : row.getPhysicalNumberOfCells();
    }

    // Get cell data (by row and column index)
 // Updated getCellData method
    public String getCellData(int rowNum, int colNum) {
        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) return "";

        XSSFCell cell = row.getCell(colNum);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue()); // Convert double to int
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


    // Fetch all data from the Excel sheet
    public Object[][] getAllData() {
        int rowCount = getRowCount();
        int colCount = getColumnCount();

        Object[][] data = new Object[rowCount - 1][colCount]; // Exclude header row

        for (int i = 1; i < rowCount; i++) {  // Start from row 1 (Skip header)
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }

    // Fetch only usernames from a specific column (Assuming Username is in Column Index 0)
    public Object[][] getUserNames() {
        int rowCount = getRowCount();
        Object[][] usernames = new Object[rowCount - 1][1];  // Exclude header row

        for (int i = 1; i < rowCount; i++) {  // Start from row 1 (Skip header)
            usernames[i - 1][0] = getCellData(i, 0);  // Assuming Username is in Column 0
        }
        return usernames;
    }

    // Close workbook
    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}
