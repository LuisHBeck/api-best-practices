package br.com.alura.adopet.api.domain.tutor.service.validation.registration;

import br.com.alura.adopet.api.domain.tutor.dto.TutorRegistrationData;

public interface TutorRegistrationValidator {
    void validate(TutorRegistrationData data);
}
