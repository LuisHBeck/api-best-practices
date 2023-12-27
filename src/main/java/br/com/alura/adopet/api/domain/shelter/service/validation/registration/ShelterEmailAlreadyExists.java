package br.com.alura.adopet.api.domain.shelter.service.validation.registration;

import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import br.com.alura.adopet.api.domain.shelter.repository.ShelterRepository;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShelterEmailAlreadyExists implements ShelterRegistrationValidator{

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public void validate(ShelterRegistrationData data) {
        var alreadyExists = shelterRepository.existsByEmail(data.email());
        if(alreadyExists) throw new ValidException("A shelter with that email already exists!");
    }
}
