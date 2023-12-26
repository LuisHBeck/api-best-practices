package br.com.alura.adopet.api.domain.adoption.service.validation.request;

import br.com.alura.adopet.api.domain.adoption.dto.AdoptionRequestData;

public interface AdoptionRequestValidator {
    void validate(AdoptionRequestData adoption);
}
