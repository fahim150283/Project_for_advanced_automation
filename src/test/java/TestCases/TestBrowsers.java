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
import java.util.concurrent.TimeUnit;

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

        driver.manage().window().setPosition(new Point(-2000, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        Boolean until = wait.until(
                d -> {
                    try {
                        // navigate to google
                        driver.get("https://qa.way2automation.com/");
                        log.info("Logger: {}", driver.getCurrentUrl());
                        log.info("Form submitted successfully!");

//                        Thread.sleep(3000);
                        //xpath of phone
                        // Locate the form elements using relative locators
                        WebElement nameLabel = driver.findElement(By.xpath("//label[text()='Name:']"));
                        WebElement nameField = driver.findElement(with(By.tagName("input")).toRightOf(nameLabel));
                        nameField.sendKeys("John");

//                        WebElement phoneLabel = driver.findElement(By.xpath("//label[text()='Phone:']"));
//                        WebElement phoneField = driver.findElement(with(By.tagName("input")).toRightOf(phoneLabel));
//                        phoneField.sendKeys("1234567890");
//
//                        WebElement emailLabel = driver.findElement(By.xpath("//label[text()='Email:']"));
//                        WebElement emailField = driver.findElement(with(By.tagName("input")).toRightOf(emailLabel));
//                        emailField.sendKeys("john.doe@example.com");
//
//                        WebElement countryLabel = driver.findElement(By.xpath("//label[text()='Country:']"));
//                        WebElement countryDropdown = driver.findElement(with(By.tagName("select")).toRightOf(countryLabel));
//                        countryDropdown.sendKeys("Bangladesh");
//
//                        WebElement cityLabel = driver.findElement(By.xpath("//label[text()='City:']"));
//                        WebElement cityField = driver.findElement(with(By.tagName("input")).toRightOf(cityLabel));
//                        cityField.sendKeys("Dhaka");
//
//                        WebElement usernameLabel = driver.findElement(By.xpath("//label[text()='Username:']"));
//                        WebElement usernameField = driver.findElement(with(By.tagName("input")).toRightOf(usernameLabel));
//                        usernameField.sendKeys("john_doe");
//
//                        WebElement passwordLabel = driver.findElement(By.xpath("//label[text()='Password:']"));
//                        WebElement passwordField = driver.findElement(with(By.tagName("input")).toRightOf(passwordLabel));
//                        passwordField.sendKeys("password123");
//
//                        // Submit the form
//                        WebElement submitButton = driver.findElement(with(By.tagName("input")).below(passwordField));
//                        submitButton.click();

                    } catch (NoAlertPresentException e) {
                        return true; // Alert has disappeared
                    }
                    return true;
                });


    }

    @AfterTest
    public static void afterTest() throws InterruptedException {
        Thread.sleep(5000);
//        driver.close();     //close current browser
        driver.quit();      //close all the browsers
    }
}
