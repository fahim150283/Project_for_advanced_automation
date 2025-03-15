package Utilities;

import org.testng.*;
import org.testng.reporters.EmailableReporter2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestListeners extends TestBrowsers implements ITestListener, ISuiteListener {
    private ISuite suite;
    public static String REPORT_DIR;
    public static String reportFolderName;

    @Override
    public void onStart(ISuite suite) {
        this.suite = suite;
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName());
        // Generate timestamped report name
        REPORT_DIR = "Reports for Email";
        File reportFolder = new File(REPORT_DIR);
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }
        String timestamp = new SimpleDateFormat("dd_MM_yy hh_mm a").format(new Date());
        reportFolderName = REPORT_DIR + "/TestNG Report of " + timestamp;

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        try {
            TestScreenshotUsingAshot.sshot(methodName,reportFolderName);  // Capture screenshot on failure
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

        System.out.println("All tests finished. Waiting for TestNG to finalize reports...");

        try {
            Thread.sleep(5000);  // Wait for TestNG to finalize reports
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure the report directory exists
        File reportDir = new File(reportFolderName);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Generate the TestNG emailable report
        System.out.println("Generating TestNG emailable report...");
        EmailableReporter2 report = new EmailableReporter2();
        report.generateReport(
                List.of(context.getSuite().getXmlSuite()),  // Ensure XmlSuite is correctly provided
                List.of(context.getSuite()),                // Ensure ISuite list is correctly provided
                reportFolderName                            // Correct output directory
        );

        System.out.println("Report generated successfully!");

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



    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}