package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestBrowsers {


    public static Logger log = LogManager.getLogger(TestBrowsers.class.getName());
    static WebDriver driver;
    static String browser = "chrome";

    //variables for ease of code
    public static String xpath;
    public static String id;
    public static String cssSelector;


    @BeforeTest
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        WebDriver.Timeouts timeouts = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
//        driver.manage().window().minimize();
    }



    @Test
    public static void test1() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        log.info("Logger : ", driver.getCurrentUrl());

        Thread.sleep(2000);

        //username
        xpath = "//*[@id=\"login_credentials\"]/text()[1]";
        String username = String.valueOf(driver.findElement(By.xpath(xpath)));
        //password
        xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/text()";
        String password = String.valueOf(driver.findElement(By.xpath(xpath)));

        System.out.println(username + " " + password);

        //username field
        id = "user-name";
        driver.findElement(By.id(id)).sendKeys(username);
        //password field
        id = "password";
        driver.findElement(By.id(id)).sendKeys(password);

        //click the login button
        id = "login-button";
        driver.findElement(By.id(id)).click();
    }

    @AfterTest
    public static void afterTest() throws InterruptedException {
        Thread.sleep(2000);
//        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
