package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.model.Adoption;

public interface AdoptionRequestValidator {
    void validate(Adoption adoption);
}
