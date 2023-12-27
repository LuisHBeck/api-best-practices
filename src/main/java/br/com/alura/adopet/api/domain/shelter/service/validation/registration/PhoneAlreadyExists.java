package br.com.alura.adopet.api.domain.shelter.service.validation.registration;

import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import br.com.alura.adopet.api.domain.shelter.repository.ShelterRepository;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneAlreadyExists implements ShelterRegistrationValidator{

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public void validate(ShelterRegistrationData data) {
        var alreadyExists = shelterRepository.existsByPhone(data.phone());
        if(alreadyExists) throw new ValidException("A shelter with that phone already exists!");
    }
}
