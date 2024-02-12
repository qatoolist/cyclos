package com.qaneha.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestData {
    public static String[] getData(String filePath) {
        String[] data = new String[2]; // Assuming you always want to return two pieces of data

        // Use try-with-resources to ensure resources are closed properly
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0); // Get the first sheet

            // Check cell type before retrieving values to handle NUMERIC and STRING cells
            data[0] = getCellStringValue(sheet.getRow(0).getCell(0));
            data[1] = getCellStringValue(sheet.getRow(0).getCell(1));

        } catch (IOException e) {
            // Consider logging this error or handling it in a way that's appropriate for your application
            throw new RuntimeException("Error reading test data from file: " + filePath, e);
        }

        return data;
    }

    private static String getCellStringValue(org.apache.poi.ss.usermodel.Cell cell) {
        // This method checks the cell type and returns the value as a String
        if (cell == null) return ""; // Return empty string for null cells
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> ""; // Return empty string for other types
        };
    }
}
