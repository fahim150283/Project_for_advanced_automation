package TestCases.Assignments.Section29;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Assignment1 extends Utilities.Setup {

    @Test
    public void assignment1() {
        fluentwait.until(d -> {
            try {
                // navigate to google
//                driver.get("https://www.makemytrip.com/");
                driver.get("https://www.google.com/");
                xpath = "//*[@id=\"SW\"]/div[3]/div/div/a[1]";
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).click();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } return true;
        });
    }
}
