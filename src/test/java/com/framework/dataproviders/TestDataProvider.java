package com.framework.dataproviders;

import com.framework.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "FormDataFromExcel")
    public static Object[][] getFormData() {
        return ExcelUtil.getSheetData("src/test/resources/testdata.xlsx", "Sheet1");
    }

    @DataProvider(name = "DirectFormData")
    public static Object[][] getDirectFormData() {
        return new Object[][]{
            {"John Doe", "john.doe@test.com", "9876543210", "123 Main St, NY", "Male", "United States"},
            {"John Die", "john.doe@test.com", "9866543210", "123 Main St, NY", "Male", "United States"},
            {"Jane Smith", "jane.smith@test.com", "9123456780", "456 Elm St, London", "Female", "United Kingdom"}
        };
    }
}