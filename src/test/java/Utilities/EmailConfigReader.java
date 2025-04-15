package Utilities;

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EmailConfigReader {

    private String mailServer;
    private String from;
    private String password;
    private List<String> recipients;

    public EmailConfigReader() {
        try {
            // Load the JSON file from resources
            InputStream is = getClass().getResourceAsStream("/EmailCredentials.json");
            JSONTokener tokener = new JSONTokener(is);
            JSONObject config = new JSONObject(tokener);

            // Get email object
            JSONObject email = config.getJSONObject("email");

            // Extract values
            this.mailServer = email.getString("mailServer");
            this.from = email.getString("from");
            this.password = email.getString("password");

            // Extract recipients array
            this.recipients = new ArrayList<>();
            for (int i = 0; i < email.getJSONArray("recipients").length(); i++) {
                recipients.add(email.getJSONArray("recipients").getString(i));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read email configuration", e);
        }
    }

    // Getters
    public String getMailServer() { return mailServer; }
    public String getFrom() { return from; }
    public String getPassword() { return password; }
    public List<String> getRecipients() { return recipients; }

    // Usage example
    public static void main(String[] args) {
        EmailConfigReader config = new EmailConfigReader();

        System.out.println("Mail Server: " + config.getMailServer());
        System.out.println("From: " + config.getFrom());
        System.out.println("Password: " + config.getPassword());
        System.out.println("Recipients: " + config.getRecipients());
    }
}