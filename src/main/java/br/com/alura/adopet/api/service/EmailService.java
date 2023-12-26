package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.domain.adoption.model.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMailMessage(Adoption adoption, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@gmail.com.br");
        email.setTo(adoption.getPet().getAbrigo().getEmail());
        email.setSubject(subject);
        email.setText("Olá " +adoption.getPet().getAbrigo().getNome() + message + adoption.getPet().getNome() +". \nPlease rate for approval or disapproval.");
        emailSender.send(email);
    }
}
