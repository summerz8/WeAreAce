/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author summer
 */
public class SendEmail {

    public SendEmail() {
    }
    
    public Boolean sendWelcomeMessage(String userAccount, String newPass) {
        System.out.println(userAccount);
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.user", "islandfurnitureit03krt@gmail.com");
//        props.put("mail.smtp.password", "IS3102IT03");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("islandfurnitureit03krt@gmail.com", "IS3102IT03");
            }
        };
        Session session = Session.getInstance(props, auth);
        System.out.println("Session created");

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userAccount));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userAccount));
            message.setSubject("Member Registration Successfully!");
            message.setText("Thank you for registering with Island Furniture and your temporary password is "
                    +newPass);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
