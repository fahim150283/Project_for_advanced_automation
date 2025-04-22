package Grid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class TestParallel {

    SoftAssert softAssert = new SoftAssert();
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser", "headless", "url"})
    @Test
    public void doSearch(String browser, String headless, String url) throws MalformedURLException {
        RemoteWebDriver dr = null;
        try {
            if (browser.equals("firefox")) {
                FirefoxOptions opt = new FirefoxOptions();
                dr = new RemoteWebDriver(new URL(url), opt);
            } else if (browser.equals("chrome")) {
                ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--no-sandbox");
            opt.addArguments("--disable-dev-shm-usage");
            opt.addArguments("--remote-allow-origins=*");
            opt.addArguments("--user-data-dir=/tmp/unique-profile-" + System.currentTimeMillis());
                dr = new RemoteWebDriver(new URL(url), opt);
            } else if (browser.equals("edge")) {
                EdgeOptions opt = new EdgeOptions();
                opt.addArguments("--no-sandbox");
                opt.addArguments("--disable-dev-shm-usage");
                opt.addArguments("--remote-allow-origins=*");
                opt.addArguments("--user-data-dir=/tmp/unique-profile-" + System.currentTimeMillis());
                dr = new RemoteWebDriver(new URL(url), opt);
            }

            driver.set(dr);
            System.out.println(browser + " opened");
            softAssert.assertTrue(driver.get().getCurrentUrl().contains("google"));

            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get().quit();
        driver.remove();
    }
}
