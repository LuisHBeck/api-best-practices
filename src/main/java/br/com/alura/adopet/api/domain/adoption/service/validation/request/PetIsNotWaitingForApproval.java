package br.com.alura.adopet.api.domain.adoption.service.validation.request;

import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import br.com.alura.adopet.api.domain.adoption.model.AdoptionStatus;
import br.com.alura.adopet.api.domain.adoption.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PetIsNotWaitingForApproval implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var isPetWaitingForApproval = adoptionRepository.existsByPetIdAndStatus(
                adoptionRequestData.petId(),
                AdoptionStatus.AWAITING_EVALUATION);

        if(isPetWaitingForApproval) throw new ValidException("Dog is already awaiting an adoption evaluation to be adopted");
    }
}
