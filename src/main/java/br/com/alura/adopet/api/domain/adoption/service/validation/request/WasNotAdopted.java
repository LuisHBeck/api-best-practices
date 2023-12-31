package br.com.alura.adopet.api.domain.adoption.service.validation.request;

import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import br.com.alura.adopet.api.domain.adoption.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WasNotAdopted implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var adoption = adoptionRepository.getReferenceById(adoptionRequestData.petId());
        if (adoption.getPet().getAdopted()) throw new ValidException("Dog already adopted!");
    }
}
