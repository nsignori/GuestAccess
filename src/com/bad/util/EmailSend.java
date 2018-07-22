package com.bad.util;
/*
 * Code from https://www.youtube.com/watch?v=UMfjndwGwnM
 * https://www.google.com/settings/security/lesssecureapps
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
public class EmailSend {
	public static boolean sendMail(String to, String message){
		boolean success = false;
		try{
			String host ="smtp.gmail.com" ;
			String user = "myplacehackers@gmail.com"; 
			String pass = "TeamPassword1";
			String from = user;
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);

			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addresses = InternetAddress.parse(to);
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setSentDate(new Date());
			msg.setText(message);

			Transport transport=mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Message sent successfully");
			success = true;
		} catch(Exception ex) {
			System.out.println(ex);
		}
		
		return success;
	}
}
