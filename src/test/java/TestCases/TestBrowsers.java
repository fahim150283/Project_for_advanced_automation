package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestBrowsers {


    public static Logger log = LogManager.getLogger(TestBrowsers.class.getName());
    static WebDriver driver;
    static String browser = "firefox";

    //variables for ease of code
    public static String xpath;
    public static String id;
    public static String cssSelector;
    static Wait<WebDriver> wait;

    @BeforeTest
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }

        //waits
        {
            // implicit
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            // explicit
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Fluent
            wait =
                    new FluentWait<>(driver)
                            .withTimeout(Duration.ofSeconds(10))
                            .pollingEvery(Duration.ofMillis(900))
                            .ignoring(NoAlertPresentException.class);
        }

        driver.manage().window().maximize();
//        driver.manage().window().minimize();
    }


    @Test
    public static void test1() throws InterruptedException {
        wait.until(
                d -> {
                    try {
                        driver.get("https://www.saucedemo.scom/");
                        log.info("Logger: {}", driver.getCurrentUrl());

                        //value of login button
                        xpath = "//form/input";

                        String login = driver.findElement(By.xpath(xpath)).getAttribute("value");
                        System.out.println(login);

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    }
                    return true;
                });



    }


    @AfterTest
    public static void afterTest() throws InterruptedException {
        Thread.sleep(2000);
//        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
