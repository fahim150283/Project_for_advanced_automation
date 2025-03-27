package TestCases.Assignments.Section29;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Assignment1 extends Utilities.Setup {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void assignment1() {
        fluentwait.until(d -> {
            try {
                driver.get("https://www.makemytrip.com/");
                xpath = "//*[@id=\"SW\"]/div[3]/div/div/a[2]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //round trip
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[1]/ul/li[2]/span";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //from
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/input";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //search for a place
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/input";
                driver.findElement(By.xpath(xpath)).sendKeys("new york");

                //select the first option
                xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //to
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/input";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //search for a place
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/input";
                driver.findElement(By.xpath(xpath)).sendKeys("london");

                //select the first option
                xpath = "//*[@id=\"react-autowhatever-1-section-0-item-0\"]/div";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //select first date
                List<WebElement> availabledates = wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) By.xpath("//div[contains(@class, 'DayPicker-Day') and not(contains(@class, 'DayPicker-Day--disabled'))]")));
                // Click the first available date
                if (!availabledates.isEmpty()) {
                    availabledates.get(0).click();
                }

                //select second date
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/div[1]/div[4]/div[2]";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();
                List<WebElement> availabledates2 = wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) By.xpath("//div[contains(@class, 'DayPicker-Day') and not(contains(@class, 'DayPicker-Day--disabled'))]")));
                // Click the first available date
                if (!availabledates2.isEmpty()) {
                    availabledates2.get(0).click();
                }


                //click search
                xpath = "//*[@id=\"top-banner\"]/div[2]/div/div/div/div/div[2]/p/a";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).click();

                //Scroll to load more items
                xpath = "//*[@id=\"listing-id\"]/div/div[2]/div/div[1]";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"listing-id\"]/div/div[2]/div/div[1]")));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                for (int i = 0; i < 30; i++) {
                    js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                    Thread.sleep(400);
                }
                js.executeScript("window.scrollTo(0, 0);");

                //get the information of the flights
                xpath = "//div[@data-test=\"component-clusterItem\"]";



            } catch (RuntimeException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        });
        softAssert.assertAll();
    }
}
