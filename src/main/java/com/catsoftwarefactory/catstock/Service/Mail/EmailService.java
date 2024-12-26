package com.catsoftwarefactory.catstock.Service.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo);

            javaMailSender.send(mensaje);
            System.out.println("Correo enviado con éxito a " + destinatario);
        } catch (MessagingException e) {
            System.out.println("Error al enviar correo electrónico: " + e.getMessage());
        }
    }
}
