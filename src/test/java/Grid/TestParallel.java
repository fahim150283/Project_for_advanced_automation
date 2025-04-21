package Grid;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestParallel {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser", "url"})
    @Test
    public void doSearch(String browser, String url) throws MalformedURLException {
        Capabilities opt = null; // Declare the opt variable
        if (browser.equals("firefox")) {
            opt = new FirefoxOptions();
        } else if (browser.equals("chrome")) {
            opt = new ChromeOptions();
//            opt.addArguments("--no-sandbox");
//            opt.addArguments("--disable-dev-shm-usage");
//            opt.addArguments("--remote-allow-origins=*");
//            opt.addArguments("--user-data-dir=/tmp/unique-profile-" + System.currentTimeMillis());
        } else if (browser.equals("edge")) {
            opt = new EdgeOptions();
        }
        driver.set(new RemoteWebDriver(new URL(url), opt)); // No need to cast opt to Capabilities
        System.out.println(browser + " opened");
    }
}
