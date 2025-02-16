package TestCases;

import ExcelReader.DataProviders;
import org.testng.annotations.Test;

public class NewTestForDataProvider {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp3")
    public void testLogin(String firstName, String lastName) {
        System.out.println(firstName + " " + lastName);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dp3")
    public void testUserReg(String firstName, String lastName, String email) {
        System.out.println(firstName + " " + lastName + " " + email);
    }
}
