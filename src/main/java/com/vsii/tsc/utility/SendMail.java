package com.vsii.tsc.utility;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

 public static void execute() throws Exception {
  //Define variables
  Properties p = CommonOperations.readConfig();
  final String fromEmail = p.getProperty("fromEmail");
  final String password = p.getProperty("password");
  final String receivedEmail = p.getProperty("receivedEmail");
  String emailSubject = p.getProperty("emailSubject");
  String emailContent = p.getProperty("emailContent");

  Properties props = new Properties();
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.port", "587");

  //Make authentication
  Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(fromEmail, password);
     }
    });

  try {

   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress(fromEmail));
   message.setRecipients(Message.RecipientType.TO,
     InternetAddress.parse(receivedEmail));
   message.setSubject(emailSubject);
   message.setText(emailContent);

   MimeBodyPart messageBodyPart = new MimeBodyPart();

   Multipart multipart = new MimeMultipart();

   messageBodyPart = new MimeBodyPart();
   String reportFile = p.getProperty("reportPath");
   DataSource source = new FileDataSource(reportFile);
   messageBodyPart.setDataHandler(new DataHandler(source));
   messageBodyPart.setFileName(reportFile);
   multipart.addBodyPart(messageBodyPart);

   message.setContent(multipart);
   System.out.println("Sending");
   Transport.send(message);
   System.out.println("Done");
  } catch (MessagingException e) {
   throw new RuntimeException(e);
  }
 }
}

