package gestionpromotion.Utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailerApi {

    public MailerApi() {
    }

    public void SendMail(String mail, String msg) {

        final String username = "testapimail63@gmail.com";
        final String password = "TESTapimail2022";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testapimail63@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(mail)
            );
            message.setSubject("Mail de Confirmation");
            message.setText("Promotion Créer avec succes, " + msg);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("ereur mailer");
        }
    }

}