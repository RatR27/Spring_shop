package com.rr27.lesson4springdata.services;

import com.rr27.lesson4springdata.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailService {
    private JavaMailSender sender;
    private MailMessageBuilder messageBuilder;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @Autowired
    public void setMessageBuilder(MailMessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    private void sendMail(String email, String subject, String text) {
        MimeMessage message = sender.createMimeMessage();
        //через helper идет наполнение нашего сообщения
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
            helper.setTo(email);                        //кому отправляем
            helper.setText(text, true);           //тело сообщения - это html страница на самом деле
            helper.setSubject(subject);                 //тема сообщения
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            executorService.submit(() -> sender.send(message));         //если все ОК, то отправляем отдельным потоком
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    //сам метод отправки
    public void sendOrderMail(Order order) {
        sendMail(order.getUser().getEmail()
                , String.format("Заказ %d%n отправлен в обработку", order.getId())
                , messageBuilder.buildOrderEmail(order));
    }


}
