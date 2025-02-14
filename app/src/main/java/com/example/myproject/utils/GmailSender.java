package com.example.myproject.utils;

import android.os.AsyncTask;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {
    private final static String smtpHost = "smtp.mailtrap.io";
    private final static int smtpPort = 465; // Mailtrap uses port 2525, 465, or 587
    private final static String username = "99b8f1ecbe42ac"; // Mailtrap username
    private final static String password = "f3b54fa5dd74ca"; // Mailtrap password

    public static void sendEmail(String recipientEmail, String subject, String body) {
        new SendEmailTask().execute(recipientEmail, subject, body);
    }

    private static class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String recipientEmail = params[0];
            String subject = params[1];
            String body = params[2];

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", smtpHost);
            properties.put("mail.smtp.port", smtpPort);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("your-email@example.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                System.out.println("Email sent successfully!");
            } else {
                System.out.println("Failed to send email.");
            }
        }
    }
}

