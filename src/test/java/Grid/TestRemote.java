package Grid;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRemote {
    static WebDriver driver;
    public static void main(String[] args) throws MalformedURLException {
        try {
//            ChromeOptions co = new ChromeOptions();
//            EdgeOptions eo = new EdgeOptions();
            FirefoxOptions fo = new FirefoxOptions();
//            driver = new RemoteWebDriver(new URL("http://192.168.30.234:4444"), co);
            driver = new RemoteWebDriver(new URL("http://192.168.30.234:4444"), fo);
//            driver = new RemoteWebDriver(new URL("http://192.168.30.234:4444"), eo);
            driver.get("http://www.way2automation.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
