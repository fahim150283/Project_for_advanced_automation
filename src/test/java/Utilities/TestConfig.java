package Utilities;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TestConfig {
    public static String mailServer;
    public static String from;
    public static String password;
    public static String[] to;
    public static String subject;
    public static String messageBody;
    public static String[] attachmentPaths;

    static {
        mailServer = "smtp.gmail.com";
        from = "ahchowdhury.off@gmail.com";
        password = "pumisiskddaexjcc"; // Make sure this is an App Password, NOT your real password!

        to = new String[]{
                "fahim150283@gmail.com",
                "hixopuli@teleg.eu",
                "piknitokki@gufum.com",
                "sejanac462@calmpros.com"
        };

        subject = "Test Report of Automation Test";
        messageBody = "Yo Yo, this is the test email containing the automation test report. Please find the attached files.";

        attachmentPaths = new String[]{
                "screenshot/testScreenshotOnFail-2025_03_03-09_39.jpg",
                "logs/app-2025-03-02_10-29-15.log"
        };

        // Call sendMail after configuration
        MonitoringMail.sendMail(mailServer, from, password, to, subject, messageBody, attachmentPaths);
    }
}


