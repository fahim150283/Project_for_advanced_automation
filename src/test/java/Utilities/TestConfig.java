package Utilities;

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
        password = "pumisiskddaexjcc"; // this is an App Password

        to = new String[]{
                "fahim150283@gmail.com",
                "niltikerka@gufum.com",
                "pkogid23583@oziere.com"
        };

        subject = "Test Report of Automation Test";
        messageBody = "Yo Yo, this is the test email containing the automation test report. Please find the attached files.";

        attachmentPaths = new String[]{
                "Reports/report.zip"
        };

        try {
            Thread.sleep(3000); // Wait 5 seconds to ensure all screenshots & logs are saved
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Call sendMail after configuration
//        MonitoringMail.sendMail(
//                mailServer,
//                from,
//                password,
//                to,
//                subject,
//                messageBody,
//                attachmentPaths
//        );
    }
}


