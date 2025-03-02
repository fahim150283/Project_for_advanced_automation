package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.log4testng.Logger.getLogger;

public  class TestBrowsers {

    public static Logger log;

    static {
        try {
            log = getLogger(Class.forName(TestBrowsers.class.getName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver driver;
//    public static ThreadLocal<WebDriver> ThreadLocal_driver = new ThreadLocal<>();
    static String browser;

    //variables for ease of code
    public static String xpath;
    public static String id;
    public static String cssSelector;
    public static Wait<WebDriver> fluentwait;
    static WebDriverWait wait;

    private WebDriver getDriver(String browser) {
        System.out.println("✅ Selected Browser: " + browser); // Debugging output
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
//            System.out.println("✅ Firefox Driver initialized!");
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
//            System.out.println("✅ Chrome Driver initialized!");
        }
        return driver;
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("firefox") String browser) {
        driver = getDriver(browser);
//        ThreadLocal_driver.set(driver);

        //waits
        {
            // implicit
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            // explicit
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Fluent
            fluentwait =
                    new FluentWait<>(driver)
                            .withTimeout(Duration.ofSeconds(5))
                            .pollingEvery(Duration.ofMillis(900))
                            .ignoring(NoAlertPresentException.class);
        }

        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();
//        driver.manage().window().minimize();
    }

    @AfterMethod
    public static void afterTest() throws InterruptedException {
//        Thread.sleep(2000);
// close all the browsers
//        if (ThreadLocal_driver.get() != null) {
//            ThreadLocal_driver.get().close();
//        }
        if (driver != null) {
            driver.close();
        }
    }


    public static int calculateResult(String input) {
        try {
            // Use regex to extract the operands and the operator. Improved regex to handle spaces
            Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)\\s*=");
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                int operand1 = Integer.parseInt(matcher.group(1));
                String operator = matcher.group(2);
                int operand2 = Integer.parseInt(matcher.group(3));

                switch (operator) {
                    case "+":
                        return operand1 + operand2;
                    case "-":
                        return operand1 - operand2;
                    case "*":
                        return operand1 * operand2;
                    case "/":
                        return operand1 / operand2; // Be cautious about division by zero
                    default:
                        return Integer.MIN_VALUE; // Indicate invalid operator
                }
            } else {
                return Integer.MIN_VALUE; // Indicate no match/invalid input
            }
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE; // Indicate parsing error
        } catch (ArithmeticException e) {
            return Integer.MIN_VALUE; // Indicate arithmetic error like division by zero
        }
    }
}