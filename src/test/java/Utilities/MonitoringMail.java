package Utilities;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;

public class MonitoringMail {

    public static void sendMail(String mailServer, String from, String password, String[] to, String subject, String messageBody, String[] attachmentPaths) {
        boolean debug = false;
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

            // Send email
            Transport.send(message);
            System.out.println("Successfully sent mail with attachments!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Properties getProperties(String mailServer) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.port", "465"); // Use TLS (better than SSL 465)
        return props;
    }

    // Fixed Authenticator to use credentials passed to sendMail()
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

