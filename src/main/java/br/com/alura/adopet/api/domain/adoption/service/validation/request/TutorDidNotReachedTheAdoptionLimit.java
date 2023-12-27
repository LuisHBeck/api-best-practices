package br.com.alura.adopet.api.domain.adoption.service.validation.request;

import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import br.com.alura.adopet.api.domain.adoption.model.AdoptionStatus;
import br.com.alura.adopet.api.domain.adoption.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorDidNotReachedTheAdoptionLimit implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var tutorAdoptionsCount = adoptionRepository.countByTutorIdAndStatus(
                adoptionRequestData.tutorId(),
                AdoptionStatus.APPROVED);

        if(tutorAdoptionsCount == 5) throw new ValidException("Tutor has reached the maximum limit of 5 adoptions");
    }
}
