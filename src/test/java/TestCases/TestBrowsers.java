package TestCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBrowsers {


    public static Logger log = LogManager.getLogger(TestBrowsers.class.getName());
    static ChromeDriver driver;
    static FirefoxDriver ffDriver;

    @BeforeTest
    public void setUp() {
        driver= new ChromeDriver();
        driver.manage().window().maximize();
//        ffDriver = new FirefoxDriver();
//        ffdriver.manage().window().maximize();
    }

    @Test
    public static void test123() {

        driver.get("https://www.google.com");
        log.info("Logger: {}", driver.getCurrentUrl());

        //get title
        String title = driver.getTitle();

        System.out.println(title);


    }

    @AfterTest
    public static void afterTest() {
        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
