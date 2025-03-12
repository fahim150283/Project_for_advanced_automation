package Utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Utilities.MonitoringMail.zipScreenshots;

public class TestConfig {
    public static String mailServer;
    public static String from;
    public static String password;
    public static String[] to;
    public static String subject;
    public static String messageBody;
    public static String[] attachmentPaths;
    public static String zipfilepath,folderpath;

    static {
        mailServer = "smtp.gmail.com";
        from = "ahchowdhury.off@gmail.com";
        password = "pumisiskddaexjcc"; // this is an App Password

        to = new String[]{
                "fahim150283@gmail.com",
                "fahim150283@yahoo.com"
        };

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm a - dd/MM/yyyy");
        String formattedDate = localDateTime.format(myFormatObj);

        subject = "Automation Test Report On " + formattedDate;
        messageBody = "Yo Yo, this is the test email containing the automation test report. Please find the attached files.";


        String reportsDir = "Reports for Email";

        // Ensure "Reports for Email" directory exists
        File reportsFolder = new File(reportsDir);
        if (!reportsFolder.exists()) {
            reportsFolder.mkdirs(); // Creates directory if it doesn't exist
        }

        zipfilepath = reportsDir + "/report_" + formattedDate + ".zip";
        folderpath = "screenshot";
        zipScreenshots(folderpath, zipfilepath);
        attachmentPaths = new String[]{zipfilepath};

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}