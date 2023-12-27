package br.com.alura.adopet.api.domain.adoption.service;

import br.com.alura.adopet.api.domain.adoption.service.validation.request.AdoptionRequestValidator;
import br.com.alura.adopet.api.domain.adoption.dto.AdoptionApproveData;
import br.com.alura.adopet.api.domain.adoption.dto.AdoptionDisapprovalData;
import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;
import br.com.alura.adopet.api.domain.adoption.model.Adoption;
import br.com.alura.adopet.api.domain.adoption.repository.AdoptionRepository;
import br.com.alura.adopet.api.domain.pet.repository.PetRepository;
import br.com.alura.adopet.api.domain.tutor.repository.TutorRepository;
import br.com.alura.adopet.api.infra.service.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
