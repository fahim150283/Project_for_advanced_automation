package Grid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRemote {
    static WebDriver driver;
    static String officeUrl = "http://192.168.30.74:4444";
    static String homeUrl = "http://192.168.1.101:4444";
    static String url = homeUrl;

    @Test(priority = 1)
    public static void TestChrome() throws MalformedURLException {
        try {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--no-sandbox");
            co.addArguments("--disable-dev-shm-usage");
            co.addArguments("--remote-allow-origins=*");
            co.addArguments("--user-data-dir=/tmp/unique-profile-" + System.currentTimeMillis());
            driver = new RemoteWebDriver(new URL(url), co);
            driver.get("https://www.google.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.out.println("Test Failed for Chrome");
            throw new RuntimeException(e);
        }
        driver.quit();
        System.out.println("Test Completed Successfully for Chrome");
    }

    @Test(priority = 3)
    public static void TestEdge() throws MalformedURLException {
        try {
            EdgeOptions co = new EdgeOptions();
            co.addArguments("--no-sandbox");
            co.addArguments("--disable-dev-shm-usage");
            co.addArguments("--remote-allow-origins=*");
            co.addArguments("--user-data-dir=/tmp/unique-profile-" + System.currentTimeMillis());
            driver = new RemoteWebDriver(new URL(url), co);
            driver.get("https://www.google.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.out.println("Test Failed for Edge");
            throw new RuntimeException(e);
        }
        driver.quit();
        System.out.println("Test Completed Successfully for Edge");
    }

    @Test(priority = 2)
    public static void TestFirefox() throws MalformedURLException {
        try {
            FirefoxOptions co = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(url), co);
            driver.get("https://www.google.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.out.println("Test Failed for Firefox");
            throw new RuntimeException(e);
        }
        driver.quit();
        System.out.println("Test Completed Successfully for Firefox");
    }
}