package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

        zipfilepath = TestListeners.reportFolderName + ".zip";
        folderpath = TestListeners.reportFolderName;
        zipScreenshots(folderpath, zipfilepath);
        attachmentPaths = new String[]{zipfilepath};

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void zipScreenshots(String folderPath, String zipFilePath) {
        try {
            System.out.println("Zipping all contents of the folder: " + folderPath);

            File folder = new File(folderPath);
            if (!folder.exists() || folder.listFiles() == null || folder.listFiles().length == 0) {
                System.out.println("No files or folders to zip.");
                return;
            }

            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            for (File file : folder.listFiles()) {
                zipFile(file, file.getName(), zipOut);
            }

            zipOut.close();
            fos.close();

            System.out.println("All contents zipped successfully: " + zipFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void zipFile(File file, String zipEntryName, ZipOutputStream zipOut) throws IOException, IOException {
        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                zipFile(childFile, zipEntryName + "/" + childFile.getName(), zipOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(zipEntryName);
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
            }
        }
    }
}