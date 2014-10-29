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
 * @author dan
 */
public class SendMailSSL {

    public SendMailSSL() {
    }

    public Boolean sendMessage(String userAccount) {
        System.out.println(userAccount);
//        Message msg = new MimeMessage(mailSession);
//        try {
//            msg.setSubject("[app] Email Alert");
//            msg.setRecipient(RecipientType.TO,
//                    new InternetAddress(userAccount));
//            msg.setText("Hello This is a test");
//            Transport.send(msg);           
//        } catch (Exception me) {
//            me.printStackTrace();
//            return false;
//        }
//        return true;
//    }
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
            message.setFrom(new InternetAddress("islandfurnitureit03krt@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userAccount));
            message.setSubject("HAHAHAHA initial test");
            message.setText("test");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
