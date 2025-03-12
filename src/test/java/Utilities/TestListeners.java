package Utilities;

import TestCases.TestBrowsers;
import TestCases.TestScreenshotUsingAshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Utilities.MonitoringMail.zipScreenshots;

public class TestListeners extends TestBrowsers implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        try {
            TestScreenshotUsingAshot.sshot(methodName);  // Capture screenshot on failure
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        {
            System.out.println("All tests finished. Sending email...");

            // Send test results email with attachments
            MonitoringMail.sendMail(
                    TestConfig.mailServer,
                    TestConfig.from,
                    TestConfig.password,
                    TestConfig.to,
                    TestConfig.subject,
                    TestConfig.messageBody,
                    TestConfig.attachmentPaths  // Corrected parameter to pass multiple attachments
            );
        }
    }


    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
