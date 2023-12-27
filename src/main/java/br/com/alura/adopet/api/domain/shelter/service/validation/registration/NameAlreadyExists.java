package br.com.alura.adopet.api.domain.shelter.service.validation.registration;

import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import br.com.alura.adopet.api.domain.shelter.repository.ShelterRepository;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameAlreadyExists implements ShelterRegistrationValidator{

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public void validate(ShelterRegistrationData data) {
        var alreadyExists = shelterRepository.existsByName(data.name());
        if(alreadyExists) throw new ValidException("A shelter with that name already exists!");
    }
}
