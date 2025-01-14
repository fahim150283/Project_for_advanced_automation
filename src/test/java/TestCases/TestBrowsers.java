package TestCases;

import org.openqa.selenium.*;
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
import java.util.List;

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
                        // navigate to google
                        driver.get("https://www.google.com/");
                        log.info("Logger: {}", driver.getCurrentUrl());

                        //search for string
                        xpath = "//*[@id=\"APjFqb\"]";
                        driver.findElement(By.xpath(xpath)).sendKeys("Way2Automation");
                        driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);

                        //click the first link
                        xpath = "//*[@id=\"wxVGi\"]/div/div/div/div/div/div/div[1]/div/span/a";
                        driver.findElement(By.xpath(xpath)).click();

                        //get the count of the links
                        List<WebElement> links = driver.findElements(By.tagName("a"));
                        for (WebElement link : links) {
                            String url = link.getAttribute("href");
                            System.out.println(url);
                        }

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
