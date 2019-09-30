package am.dproc.sms.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendEmailController {
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("/email")
	@ResponseBody
	  String home() {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("narek_22@mail.ru");
//        msg.setSubject("Hi");
//        msg.setText("How are you?");

//        helper.setTo(to);
        helper.setTo(new String[]{"narek_22@mail.ru","narekmalkhasyan1@gmail.com"});
//        helper.setFrom(new InternetAddress("dproc.com@gmail.com"));
        helper.setSubject("Hi");
        helper.setText("How are you?");
        sender.send(message);
//        sender.send(msg);
    }	
    
	@RequestMapping("/propemail")
	@ResponseBody
    public String getJavaMailSender() {
        final String username = "dproc.com@gmail.com";
        final String password = "smsystem123";

        Properties props = new Properties();
     	props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dproc.com@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("narekmalkhasyan1@gmail.com, narek_22@mail.ru")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "Email Sent!";
	}
    
}
