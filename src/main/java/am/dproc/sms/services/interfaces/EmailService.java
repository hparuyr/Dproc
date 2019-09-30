package am.dproc.sms.services.interfaces;

public interface EmailService {

	String send(String msg, String subject, String to);

	String send(String msg, String subject, String[] to);

}
