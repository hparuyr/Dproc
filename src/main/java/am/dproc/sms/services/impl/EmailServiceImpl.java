package am.dproc.sms.services.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import am.dproc.sms.services.interfaces.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	JavaMailSender sender;

	@Override
	public String send(String msg, String subject, String to) {
        try {
            sendEmail(msg, subject, to);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
	@Override
	public String send(String msg, String subject, String[] to) {
		try {
			sendEmail(msg, subject, to);
			return "Email Sent!";
		}catch(Exception ex) {
			return "Error in sending email: "+ex;
		}
	}

    private void sendEmail(String msg, String subject,  String to) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg);
        sender.send(message);
    }

    private void sendEmail(String msg, String subject,  String[] to) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(msg);
        sender.send(message);
    }


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
