package Utilities;

import TestCases.TestBrowsers;
import TestCases.TestScreenshotUsingAshot;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        ITestListener.super.onFinish(context);{
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



        public static void zipScreenshots(String folderPath, String zipFilePath) {
            try {
                File folder = new File(folderPath);
                File[] files = folder.listFiles();

                if (files == null || files.length == 0) {
                    System.out.println("No screenshots to zip.");
                    return;
                }

                String today = new SimpleDateFormat("yyyy_MM_dd").format(new Date());

                FileOutputStream fos = new FileOutputStream(zipFilePath);
                ZipOutputStream zipOut = new ZipOutputStream(fos);

                boolean filesAdded = false;

                for (File file : files) {
                    if (file.isFile() && file.getName().contains(today)) { // Filter files with today's date
                        FileInputStream fis = new FileInputStream(file);
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zipOut.putNextEntry(zipEntry);

                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = fis.read(bytes)) >= 0) {
                            zipOut.write(bytes, 0, length);
                        }

                        fis.close();
                        filesAdded = true;
                    }
                }

                zipOut.close();
                fos.close();

                if (filesAdded) {
                    System.out.println("Screenshots zipped successfully.");
                } else {
                    System.out.println("No screenshots found for today's date: " + today);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
