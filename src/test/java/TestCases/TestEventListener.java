package TestCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;

public class TestEventListener extends TestBrowsers {

    @Test
    public void testEventListener() {
        fluentwait.until(d -> {
            try {

            } catch (NoAlertPresentException e) {
            }
            return true;
        });
    }
}
