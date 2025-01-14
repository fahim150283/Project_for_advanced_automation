package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TestBrowsers {


    public static Logger log = LogManager.getLogger(TestBrowsers.class.getName());
    static WebDriver driver;
    static String browser = "chrome";

    //variables for ease of code
    public static String xpath;
    public static String id;
    public static String cssSelector;
    static Wait<WebDriver> wait;

    @BeforeTest
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }

        //waits
        {
            // implicit
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            // explicit
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Fluent
            wait =
                    new FluentWait<>(driver)
                            .withTimeout(Duration.ofSeconds(10))
                            .pollingEvery(Duration.ofMillis(900))
                            .ignoring(NoAlertPresentException.class);
        }

        driver.manage().window().maximize();
//        driver.manage().window().minimize();
    }


    @Test
    public static void Assignment1() throws InterruptedException {
        wait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://www.google.com/");
                        log.info("Logger: {}", driver.getCurrentUrl());

                        //search for string
                        xpath = "//*[@id=\"APjFqb\"]";
                        driver.findElement(By.xpath(xpath)).sendKeys("Way2Automation");
                        driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);

                        //click the first link
                        xpath = "//*[@id=\"wxVGi\"]/div/div/div/div/div/div/div[1]/div/span/a";
                        driver.findElement(By.xpath(xpath)).click();

                        //get the count of the links
                        List<WebElement> links = driver.findElements(By.tagName("a"));
                        for (WebElement link : links) {
                            String url = link.getAttribute("href");
                            System.out.println(url);
                        }

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    }
                    return true;
                });


    }


    @Test
    public static void Assignment2() throws InterruptedException {
        wait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://qa.way2automation.com/");
                        log.info("Logger: {}", driver.getCurrentUrl());
                        log.info("Form submitted successfully!");
                        Thread.sleep(2000);

                        //xpath of phone
                        xpath = "//*[@id=\"load_form\"]/fieldset[2]/input";
                        //input phone
                        WebElement phone = driver.findElement(By.xpath(xpath));
                        phone.sendKeys("01234567890");
                        //input name
                        WebElement name = driver.findElement(with(By.xpath(xpath)).above(phone));
                        name.sendKeys("Tester 696969");
                        //input email
                        WebElement email = driver.findElement(with(By.xpath(xpath)).below(phone));
                        email.sendKeys("nexojaj788@fenxz.com");

                        //xpath of country
                        xpath = "//*[@id=\"load_form\"]/fieldset[4]/select";
                        //input country
                        WebElement country = driver.findElement(By.xpath(xpath));
                        Select objSelect = new Select(country);
                        objSelect.selectByVisibleText("Bangladesh");
                        //input city
                        WebElement city = driver.findElement(with(By.xpath(xpath)).below(country));
                        city.sendKeys("Dhaka");

                        //xpath of password
                        xpath = "//*[@id=\"load_form\"]/fieldset[7]/input";
                        WebElement password = driver.findElement(By.xpath(xpath));
                        password.sendKeys("123456");
                        //input username
                        WebElement username = driver.findElement(with(By.xpath(xpath)).above(password));
                        username.sendKeys("dhumbachole");

                        //submit
                        xpath = "//*[@id=\"load_form\"]/div[1]/div[1]";
                        WebElement signin = driver.findElement(By.xpath(xpath));
                        WebElement submit = driver.findElement(with(By.xpath(xpath)).toRightOf(signin));
                        submit.click();

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                });


    }

    @AfterTest
    public static void afterTest() throws InterruptedException {
        Thread.sleep(2000);
//        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
