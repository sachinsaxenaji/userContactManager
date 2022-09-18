package com.userContactManager.userContactManager.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


@Service
public class EmailService {

    boolean bo = false;

    public static boolean sendAttach(String message, String Subject, String To) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("System Properties "+ properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session  = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sachiin24saxena@gmail.com", "subhasH@98");

            }


        });

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try {


            m.setFrom("sachiin24saxena@gmail.com");
            m.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(To)));
            m.setSubject(Subject);
            String s = "C:\\Users\\om\\Desktop\\aaa.jpg";

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart imageMime = new MimeBodyPart();
            try
            {
                File file = new File(s);
                imageMime.attachFile(file);
                textMime.setText(message);
                multipart.addBodyPart(textMime);
                multipart.addBodyPart(imageMime);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            m.setContent(multipart);

            Transport.send(m);

            System.out.println("email sending");
            return true;



        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
