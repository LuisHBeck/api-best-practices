package br.com.alura.adopet.api.domain.shelter.service.validation.registration;

import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;

public interface ShelterRegistrationValidator {
    void validate(ShelterRegistrationData data);
}
