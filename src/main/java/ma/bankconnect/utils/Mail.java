package ma.bankconnect.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void sendMail(String userEmail, String msg) {
        // Sender's email ID needs to be mentioned
        String email = "daalabireda@gmail.com";
        String password = "gwlzvspijaxggbqk";
        //daalabir@gmail.com
        //udfcuhgoqjdwloan
        String smtp = "smtp.gmail.com";
        String port = "25";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host",smtp);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(email, password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            assert email != null;
            message.setFrom(new InternetAddress(email));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            // Set Subject: header field
            message.setSubject("Service de Banque centrale du Maroc : Les informations de votre compte");

            // Now set the actual message
            message.setText(msg);

            System.out.println("envoyer...");
            // Send message
            Transport.send(message);
            System.out.println("message envoyer avec succées....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
