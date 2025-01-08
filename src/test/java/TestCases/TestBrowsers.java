package TestCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.safari.SafariDriver;

public class TestBrowsers {
    static ChromeDriver driver = new ChromeDriver();
    static FirefoxDriver ffDriver = new FirefoxDriver();

    public void ChromeDriver () {
        driver.get("https://www.google.com");
    }
    public void firefoxDriver () {
        ffDriver.get("https://www.google.com");
    }

    public static void main(String[] args) {
        TestBrowsers test = new TestBrowsers();
        test.ChromeDriver();
        test.firefoxDriver();
        driver.close();
        ffDriver.close();


    }
}
