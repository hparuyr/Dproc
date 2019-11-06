package am.dproc.sms.services.impl;

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
}
