package TestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBrowsers {


    public static Logger log = LogManager.getLogger(TestBrowsers.class.getName());
    static WebDriver driver;
    static String browser = "firefox";

    @BeforeTest
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
    }

    @Test
    public static void test1() {

        driver.get("https://www.google.com");
        log.info("Logger : ", driver.getCurrentUrl());

        //get title
        String title = driver.getTitle();

//        System.out.println(title);


    }

    @Test
    public static void test2() {
        driver.get("https://www.google.com/search?q=how+to+check+if+the+chromedriver+is+closed+in+selenium+java&sca_esv=b403c232d5a77298&rlz=1C1CHBF_enBD1055BD1055&sxsrf=ADLYWIKw17Xg-nf3lX9kZzdemNs0S1cWUA%3A1736608182640&ei=tomCZ4y_Jvf2seMPj7jUkQM&oq=how+to+check+if+the+chromedriver+is+closed+in+sel&gs_lp=Egxnd3Mtd2l6LXNlcnAiMWhvdyB0byBjaGVjayBpZiB0aGUgY2hyb21lZHJpdmVyIGlzIGNsb3NlZCBpbiBzZWwqAggBMgUQIRigATIFECEYoAFIgqUBUABYy5gBcAp4AJABAJgB2QGgAb5CqgEGMC41NS40uAEDyAEA-AEBmAJDoAK2QcICBBAjGCfCAgoQIxiABBgnGIoFwgILEAAYgAQYkQIYigXCAgsQABiABBixAxiDAcICCBAAGIAEGLEDwgIUEC4YgAQYsQMYgwEYxwEYigUYrwHCAgUQABiABMICCxAAGIAEGLEDGIoFwgIIEC4YgAQYsQPCAgsQLhiABBixAxjUAsICChAAGIAEGBQYhwLCAgYQABgWGB7CAgcQABiABBgNwgILEAAYgAQYhgMYigXCAgUQABjvBcICCBAAGKIEGIkFwgIEECEYFcICBRAhGJIDwgIFECEYnwXCAggQABiABBiiBJgDAJIHBzEwLjUzLjSgB42uAw&sclient=gws-wiz-serp");
        log.info("Logger : ", driver.getCurrentUrl());
        String title = driver.getTitle();
    }

    @AfterTest
    public static void afterTest() {
//        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
