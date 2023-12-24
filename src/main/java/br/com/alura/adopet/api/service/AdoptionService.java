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
    private EmailService emailService;

    @Autowired
    private List<AdoptionRequestValidator> requestValidators;

    @Transactional
    public void request(Adoption adoption) {
        requestValidators.forEach(v -> v.validate(adoption));

        adoption.setData(LocalDateTime.now());
        adoption.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoption request", "!\n\nAn adoption application was filed today for the pet: ");
    }

    @Transactional
    public void approve(Adoption adoption) {
        adoption.setStatus(StatusAdocao.APROVADO);
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoção aprovada", "!\n\nSua adoção do pet ");
    }

    @Transactional
    public void disapprove(Adoption adoption) {
        adoption.setStatus(StatusAdocao.REPROVADO);
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoção reprovada", "!\n\nInfelizmente sua adoção do pet ");
    }
}
