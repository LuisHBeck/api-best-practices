package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.domain.adoption.validators.request.AdoptionRequestValidator;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdoptionService {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private List<AdoptionRequestValidator> requestValidators;

    @Transactional
    public void request(Adoption adoption) {
        requestValidators.forEach(v -> v.validate(adoption));

        adoption.setData(LocalDateTime.now());
        adoption.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adoptionRepository.save(adoption);

        sendMailMessage(adoption, "Adoption request", "!\n\nAn adoption application was filed today for the pet: ");
    }

    @Transactional
    public void approve(Adoption adoption) {
        adoption.setStatus(StatusAdocao.APROVADO);
        adoptionRepository.save(adoption);

        sendMailMessage(adoption, "Adoção aprovada", "!\n\nSua adoção do pet ");
    }

    @Transactional
    public void disapprove(Adoption adoption) {
        adoption.setStatus(StatusAdocao.REPROVADO);
        adoptionRepository.save(adoption);

        sendMailMessage(adoption, "Adoção reprovada", "!\n\nInfelizmente sua adoção do pet ");
    }


    // AUXILIARY FUNCTIONS
    private void sendMailMessage(Adoption adoption, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@gmail.com.br");
        email.setTo(adoption.getPet().getAbrigo().getEmail());
        email.setSubject(subject);
        email.setText("Olá " +adoption.getPet().getAbrigo().getNome() + message + adoption.getPet().getNome() +". \nPlease rate for approval or disapproval.");
        emailSender.send(email);
    }
}
