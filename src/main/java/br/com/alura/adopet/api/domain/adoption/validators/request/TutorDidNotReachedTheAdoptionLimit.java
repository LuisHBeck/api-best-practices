package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
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
                StatusAdocao.APROVADO);

        if(tutorAdoptionsCount == 5) throw new ValidException("Tutor has reached the maximum limit of 5 adoptions");
    }
}
