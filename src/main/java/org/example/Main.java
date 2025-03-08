package org.example;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String mailServer = "smtp.gmail.com";
        String from = "ahchowdhury.off@gmail.com";
        String password = "password";
        String[] to = {"fahim150283@gmail.com", "haspapedro@gufum.com"};
        String subject = "Test Email";
        String messageBody = "Hello, this is a test email.";
        String[] attachmentPaths = {"C:\\Users\\TechTeam-08\\Downloads\\list of popular mobile devices.pdf", "C:\\Users\\TechTeam-08\\Downloads\\CH-250122-001-0001.xlsx"};
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

    private static Properties getProperties(String mailServer) {
        Properties props = new Properties();
        // Explicitly setting TLSv1.2
        System.setProperty("https.protocols", "TLSv1.2");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.port", "587"); // Use 587 for STARTTLS
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        props.put("mail.smtp.ssl.enable", "false"); // Disable SSL (because we're using STARTTLS on port 587)

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
