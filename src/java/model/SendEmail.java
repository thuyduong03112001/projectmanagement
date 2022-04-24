/*
package testmail;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author HP
 */
public class SendEmail {
    public void sendEmail(int x,Account A) throws MessagingException {
        Properties pr = new Properties();
        System.out.println("Preparing to send main");
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587");
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");
        String myaccount = "beastwantlov3@gmail.com";
        String password = "anhdungzoo9";
        Session session = Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myaccount, password);
            }
        });
        if(x==1){
        Message mess = prepareMessage(session,myaccount,A.getEmail(),A.getAccount());
        Transport.send(mess);
        return;
        }
        else{
        Message mess1;
        mess1 = prepareMessage1(session,myaccount,A.getEmail(),A.getAccount());
        Transport.send(mess1);
        return;
        }      
        }
    private static Message prepareMessage(Session session,String account,String infor,String user) {
       Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(account));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(infor));
            message.setSubject("Email Verification");
            message.setText("Thank you '"+user+"' for using our service click here to start\nhttp://localhost:8080/Iter2Demo/confirm.jsp");
            return message;           
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private static Message prepareMessage1(Session session,String account,String infor,String user) {
       Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(account));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(infor));
            message.setSubject("Email Verification");
            message.setText("Thank you '"+user+"' for using our service click here to confirm reset your password\nhttp://localhost:8080/Iter2Demo/reset1.jsp");
            return message;                      
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

