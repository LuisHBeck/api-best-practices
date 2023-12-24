package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WasNotAdopted implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var adoption = adoptionRepository.getReferenceById(adoptionRequestData.pet().getId());
        if (adoption.getPet().getAdotado()) throw new ValidException("Dog already adopted!");
    }
}
