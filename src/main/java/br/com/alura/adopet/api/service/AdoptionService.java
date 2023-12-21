package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdoptionService {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public void request(Adoption adoption) {
        adoption.setData(LocalDateTime.now());
        adoption.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adoptionRepository.save(adoption);

        sendMailMessage(adoption);
    }

    public void approve() {

    }

    public void disapprove() {

    }

    public void sendMailMessage(Adoption adoption) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@gmail.com.br");
        email.setTo(adoption.getPet().getAbrigo().getEmail());
        email.setSubject("Adoption request");
        email.setText("Olá " +adoption.getPet().getAbrigo().getNome() +"!\n\nAn adoption application was filed today for the pet: " +adoption.getPet().getNome() +". \nPlease rate for approval or disapproval.");
        emailSender.send(email);
    }
}
