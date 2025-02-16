package TestCases.Parameterization;

import ExcelReader.ExcelReader;
import TestCases.TestBrowsers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestParameterization extends TestBrowsers {
    private static ExcelReader excelReader;

    @BeforeClass
    public void setup() throws IOException {
        // Initialize ExcelReader with file path
        excelReader = new ExcelReader("src/test/resources/LoginTest.xlsx");
    }

    @Test(dataProvider = "getData")
    public void DoLogin(Object[] data) {
        String username = (String) data[0];
        String password = (String) data[1];
        String isCorrect = (String) data[2];


        fluentwait.until(
                d -> {
                    System.out.println("Username: " + username + " | Password: " + password + " | isCorrect: " + isCorrect);
                    return true;
                });
    }

    @DataProvider
    public Object[][] getData() {
        String sheetname = "Sheet1";

        int rows = excelReader.getRowCount(sheetname);
        int cols = excelReader.getColumnCount(sheetname);

        Object[][] data = new Object[rows - 1][cols]; // Ignoring headers

        for (int row = 1; row < rows; row++) { // Start from 1 (skip header)
            for (int col = 0; col < cols; col++) {
                data[row - 1][col] = excelReader.getCellData(sheetname, row, col);
            }
        }
        return data;
    }
}
