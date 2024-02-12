package com.qaneha.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    private String username;
    private String password;

    public TestData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static List<TestData> readFromExcel(String filePath) throws IOException {
        List<TestData> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0); // Access the first sheet

        Row row = sheet.getRow(0); // Read the first row
        if (row != null) {
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            data.add(new TestData(username, password));
        }

        file.close();
        return data;
    }

}
