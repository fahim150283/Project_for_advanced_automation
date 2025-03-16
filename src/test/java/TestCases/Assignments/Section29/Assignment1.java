package TestCases.Assignments.Section29;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Assignment1 extends Utilities.Setup {

    @Test
    public void assignment1() {
        fluentwait.until(d -> {
            try {
                // navigate to google
                driver.get("https://www.makemytrip.com/");
                xpath = "hjag";
                driver.findElement(By.xpath(xpath));
                log.info("The navigated url is: " + driver.getCurrentUrl());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } return true;
        });
    }
}
