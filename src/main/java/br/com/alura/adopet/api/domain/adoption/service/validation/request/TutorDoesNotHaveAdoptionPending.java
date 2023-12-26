package br.com.alura.adopet.api.domain.adoption.service.validation.request;

import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.domain.adoption.model.AdoptionStatus;
import br.com.alura.adopet.api.domain.adoption.repository.AdoptionRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorDoesNotHaveAdoptionPending implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var tutorHaveAdoptionPending = adoptionRepository.existsByTutorIdAndStatus(
                adoptionRequestData.tutorId(),
                AdoptionStatus.AWAITING_EVALUATION);

        if (tutorHaveAdoptionPending) throw new ValidException("Tutor is already awaiting an adoption evaluation");
    }
}
