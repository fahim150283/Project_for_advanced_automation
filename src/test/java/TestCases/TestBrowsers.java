package TestCases;

import net.bytebuddy.implementation.FieldAccessor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.safari.SafariDriver;

import java.util.logging.Logger;

public class TestBrowsers {


    public static Logger log = Logger.getLogger(TestBrowsers.class.getName());
    static ChromeDriver driver = new ChromeDriver();
    static FirefoxDriver ffDriver = new FirefoxDriver();

    public void ChromeDriver () {
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }
    public void firefoxDriver () {
        ffDriver.manage().window().maximize();
        ffDriver.get("https://www.google.com");
    }

    public static void main(String[] args) {
        TestBrowsers test = new TestBrowsers();
        test.ChromeDriver();
        log.info("Logger: "+driver.getCurrentUrl());
        test.firefoxDriver();
        log.info("Logger: Driver closed");

        driver.close();
        ffDriver.close();
    }
}
