package Utilities;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MonitoringMail {

    public static void sendMail(String mailServer, String from, String password, String[] to, String subject, String messageBody, String[] attachmentPaths) {
        boolean debug = true; // Set to true for debugging
        Properties props = getProperties(mailServer);

        // Authenticator with correct credentials
        Authenticator auth = new SMTPAuthenticator(from, password);
        Session session = Session.getInstance(props, auth);
        session.setDebug(debug);

        try {
            Message message = new MimeMessage(session);
            message.addHeader("X-Priority", "1");
            message.setFrom(new InternetAddress(from));

            InternetAddress[] addressTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                addressTo[i] = new InternetAddress(to[i]);
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject(subject);

            // Email body
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(messageBody, "text/html");

            // Attachments
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            for (String filePath : attachmentPaths) {
                if (filePath != null && !filePath.isEmpty()) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(file);
                        attachmentPart.setDataHandler(new DataHandler(source));
                        attachmentPart.setFileName(file.getName());
                        multipart.addBodyPart(attachmentPart);
                    } else {
                        System.out.println("Attachment not found: " + filePath);
                    }
                }
            }

            message.setContent(multipart);

            for (int i = 0; i < to.length; i++) {
                System.out.println(to[i] + ": All the to");
            }

            // Send email
            Transport.send(message);
            System.out.println("Successfully sent mail with attachments!");

        } catch (MessagingException e) {
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

    private static Properties getProperties(String mailServer) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.port", "587");  // Use 587 for TLS
        props.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");  // Enable TLS 1.2 and TLS 1.3
        return props;
    }

    private static class SMTPAuthenticator extends Authenticator {
        private final String username;
        private final String password;

        public SMTPAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}

