package ExcelReader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

    @Test(dataProvider = "dp1")
    public void test(String firstName, String lastName) {
        System.out.println(firstName + " " + lastName);
    }

    @DataProvider(name = "dp1")
    public Object[][] getData() {
        Object[][] data = new Object[2][2];
        data[0][0] = "John";
        data[0][1] = "Doe";

        data[1][0] = "Jane";
        data[1][1] = "Hoe";

        return data;
    }
}

