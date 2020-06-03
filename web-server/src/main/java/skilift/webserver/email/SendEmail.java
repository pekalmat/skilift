package skilift.webserver.email;
import java.net.Authenticator;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class SendEmail {

	public static void main(String[] args) {
    	
    	Properties prop = new Properties();
    	prop.put("mail.smtp.auth", true);
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.host", "smtp.gmail.com");
    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	
    	Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication("SkiliftApplikation@gmail.com", "Skilift123");
    	    }
    	});
    	
    	Message message = new MimeMessage(session);



    	try {
    		
        	message.setFrom(new InternetAddress("SkiliftApplikation@gmail.com"));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tkollerpriv@gmail.com"));
        	message.setSubject("Test");
        	message.setText("erfolgreich");
    		
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	/*    	message.setFrom(new InternetAddress("SkiliftApplikation@gmail.com"));
    	message.setRecipients(
    	  Message.RecipientType.TO, InternetAddress.parse("tkollerpriv@gmail.com"));
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
