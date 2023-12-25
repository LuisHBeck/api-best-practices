package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.domain.adoption.validators.request.AdoptionRequestValidator;
import br.com.alura.adopet.api.dto.AdoptionApproveData;
import br.com.alura.adopet.api.dto.AdoptionDisapprovalData;
import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
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
    private TutorRepository tutorRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private List<AdoptionRequestValidator> requestValidators;

    @Transactional
    public void request(AdoptionRequestData adoptionData) {
        requestValidators.forEach(v -> v.validate(adoptionData));

        var tutor = tutorRepository.getReferenceById(adoptionData.tutorId());
        var pet = petRepository.getReferenceById(adoptionData.petId());

        var adoption = new Adoption(tutor, pet, adoptionData.reason());
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoption request", "!\n\nAn adoption application was filed today for the pet: ");
    }

    @Transactional
    public void approve(AdoptionApproveData adoptionApproveData) {
        var adoption = adoptionRepository.getReferenceById(adoptionApproveData.adoptionId());

        adoption.approve();
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoption approved", "!\n\nYour adoption has been approved!");
    }

    @Transactional
    public void disapprove(AdoptionDisapprovalData adoptionDisapprovalData) {
        var adoption = adoptionRepository.getReferenceById(adoptionDisapprovalData.adoptionId());

        adoption.disapprove();
        adoptionRepository.save(adoption);

        emailService.sendMailMessage(adoption, "Adoption disapproved", "!\n\nUnfortunately your adoption request has been disapproved!");
    }
}
