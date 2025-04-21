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

    @Parameters({"browser"})
    @Test
//    public void doSearch() throws MalformedURLException {
        public void doSearch (String browser) throws MalformedURLException {
//        String browser = "safari";
            Object opt = new Object();
            if (browser.equals("firefox")) {
                opt = new FirefoxOptions();
            } else if (browser.equals("chrome")) {
                opt = new ChromeOptions();
            } else if (browser.equals("ie")) {
                opt = new InternetExplorerOptions();
            } else if (browser.equals("safari")) {
                opt = new SafariOptions();
            } else if (browser.equals("edge")) {
                opt = new EdgeOptions();
            }
            driver.set(new RemoteWebDriver(new URL("http://192.168.30.74:4444"), (Capabilities) opt));
            System.out.println(browser + " opened");
        }
    }
