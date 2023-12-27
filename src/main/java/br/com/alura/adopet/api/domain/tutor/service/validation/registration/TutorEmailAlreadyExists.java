package br.com.alura.adopet.api.domain.tutor.service.validation.registration;

import br.com.alura.adopet.api.domain.tutor.dto.TutorRegistrationData;
import br.com.alura.adopet.api.domain.tutor.repository.TutorRepository;
import br.com.alura.adopet.api.infra.validation.exception.ValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorEmailAlreadyExists implements TutorRegistrationValidator{

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validate(TutorRegistrationData data) {
        var alreadyExists = tutorRepository.existsByEmail(data.email());
        if(alreadyExists) throw new ValidException("Tutor with that email already exists!");
    }
}
