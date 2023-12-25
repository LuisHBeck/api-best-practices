package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
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
                StatusAdocao.AGUARDANDO_AVALIACAO);

        if(isPetWaitingForApproval) throw new ValidException("Dog is already awaiting an adoption evaluation to be adopted");
    }
}
