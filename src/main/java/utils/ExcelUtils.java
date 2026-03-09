package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static Object[][] readAllData(String file, String sheet) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheetObj = workbook.getSheet(sheet);
            if (sheetObj == null) {
                return new Object[0][0];
            }
            int originalRowCount = sheetObj.getLastRowNum() + 1;
            if (originalRowCount <= 1) {
                return new Object[0][0]; // No data rows
            }
            int rowCount = originalRowCount - 1; // Skip header
            int colCount = 0;
            for (int i = 1; i < originalRowCount; i++) {
                Row row = sheetObj.getRow(i);
                if (row != null) {
                    colCount = Math.max(colCount, row.getLastCellNum());
                }
            }
            data = new Object[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                Row row = sheetObj.getRow(i + 1); // Start from row 1 (skip header)
                if (row != null) {
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    data[i][j] = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        data[i][j] = cell.getDateCellValue();
                                    } else {
                                        data[i][j] = cell.getNumericCellValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    data[i][j] = cell.getBooleanCellValue();
                                    break;
                                case FORMULA:
                                    data[i][j] = cell.getCellFormula();
                                    break;
                                default:
                                    data[i][j] = "";
                            }
                        } else {
                            data[i][j] = "";
                        }
                    }
                } else {
                    for (int j = 0; j < colCount; j++) {
                        data[i][j] = "";
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Error reading Excel file: " + e.getMessage(), e);
        }
        return data;
    }
}