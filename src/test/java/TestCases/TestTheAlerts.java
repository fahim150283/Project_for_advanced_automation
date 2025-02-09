package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class TestTheAlerts extends TestBrowsers {

    @Test
    public static void AlertAccept() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        login.sendKeys("abc");
                        WebElement pass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        pass.sendKeys("abc");
                        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
                        Thread.sleep(2000);
                        driver.switchTo().alert().accept();


                        Thread.sleep(20000);

                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                }
        );
    }

    @Test
    public static void AlertDismiss() {
        fluentwait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
                        log.info("Navigated to: " + driver.getCurrentUrl());

                        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        login.sendKeys("abc");
                        WebElement pass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login1']")));
                        pass.sendKeys("abc");
                        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
                        Thread.sleep(2000);
                        driver.switchTo().alert().dismiss();
                        Thread.sleep(2000);

                    } catch (NoAlertPresentException | InterruptedException e) {
                    }
                    return true;
                }
        );
    }
}
