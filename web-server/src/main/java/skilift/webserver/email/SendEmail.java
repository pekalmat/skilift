package skilift.webserver.email;
import java.net.Authenticator;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;



public class SendEmail {

    public static void main(String[] args) {
 /*   	
    	Properties prop = new Properties();
    	prop.put("mail.smtp.auth", true);
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.host", "smtp.mailtrap.io");
    	prop.put("mail.smtp.port", "25");
    	prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
    	
    	Session session = Session.getInstance(prop, new Authenticator() {
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication("SkiliftApplikation@gmail.com", "Skilift123");
    	    }
    	});
    	
    	Message message = new MimeMessage(session);
    	message.setFrom(new InternetAddress("from@gmail.com"));
    	message.setRecipients(
    	  Message.RecipientType.TO, InternetAddress.parse("to@gmail.com"));
    	message.setSubject("Mail Subject");
    	 
    	String msg = "This is my first email using JavaMailer";
    	 
    	MimeBodyPart mimeBodyPart = new MimeBodyPart();
    	mimeBodyPart.setContent(msg, "text/html");
    	 
    	Multipart multipart = new MimeMultipart();
    	multipart.addBodyPart(mimeBodyPart);
    	 
    	message.setContent(multipart);
    	 
    	Transport.send(message); */
    }
	
}
