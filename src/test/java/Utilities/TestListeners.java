package Utilities;

import org.testng.*;
import org.testng.reporters.EmailableReporter2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestListeners extends TestBrowsers implements ITestListener, ISuiteListener {
    private ISuite suite;

    @Override
    public void onStart(ISuite suite) {
        this.suite = suite;
    }

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
            // Generate timestamped report name
            String REPORT_DIR = "Reports for Email";
            File reportFolder = new File(REPORT_DIR);
            if (!reportFolder.exists()) {
                reportFolder.mkdirs();
            }
            String timestamp = new SimpleDateFormat("dd_MM_yy hh_mm a").format(new Date());
            String reportFileName = REPORT_DIR + "/TestNG Report of " + timestamp + ".html";

            // Generate the TestNG report
            EmailableReporter2 report = new EmailableReporter2();
            report.generateReport(
                    List.of(context.getSuite().getXmlSuite()),  // Fix: Provide List<XmlSuite>
                    List.of(context.getSuite()),                // Fix: Provide List<ISuite>
                    REPORT_DIR                      // Fix: Output directory
            );

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