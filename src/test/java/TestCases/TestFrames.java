package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class TestFrames extends TestBrowsers {
     @Test
    public static void Frames() {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_noframes");
         List<WebElement> frame = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("iframe")));
         System.out.println(frame.size());
     }
}
