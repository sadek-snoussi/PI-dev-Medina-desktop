/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PC
 */
public class Mail {

    public static void sendMail(String address, String subject, String message) throws AddressException, MessagingException {

        final String username = "youthvision.soukmedina@gmail.com";
        final String password = "SOUK2018";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password); //To change body of generated methods, choose Tools | Templates.
            }
        });

        try {

            Message m = new MimeMessage(session);
            m.setFrom(new InternetAddress("youthvision.soukmedina@gmail.com"));
            m.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            m.setSubject(subject);
            m.setText(message);

            Transport.send(m);

            System.out.println("Done");

        } catch (MessagingException e) {
//            throw new RuntimeException(e);

        }
    }

}
