package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
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
                StatusAdocao.AGUARDANDO_AVALIACAO);

        if (tutorHaveAdoptionPending) throw new ValidException("Tutor is already awaiting an adoption evaluation");
    }
}
