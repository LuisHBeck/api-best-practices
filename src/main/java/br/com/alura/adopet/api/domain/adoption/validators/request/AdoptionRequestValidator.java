package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.model.Adoption;

public interface AdoptionRequestValidator {
    void validate(AdoptionRequestData adoption);
}
