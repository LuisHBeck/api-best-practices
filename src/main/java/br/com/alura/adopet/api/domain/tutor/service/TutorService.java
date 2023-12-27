package br.com.alura.adopet.api.domain.tutor.service;

import br.com.alura.adopet.api.domain.tutor.dto.TutorDetailingData;
import br.com.alura.adopet.api.domain.tutor.dto.TutorRegistrationData;
import br.com.alura.adopet.api.domain.tutor.model.Tutor;
import br.com.alura.adopet.api.domain.tutor.repository.TutorRepository;
import br.com.alura.adopet.api.domain.tutor.service.validation.registration.TutorRegistrationValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private List<TutorRegistrationValidator> registrationValidator;

    @Transactional
    public TutorDetailingData register(TutorRegistrationData data) {
        registrationValidator.forEach(v -> v.validate(data));

        var tutor = new Tutor(data);
        tutorRepository.save(tutor);

        return new TutorDetailingData(tutor);
    }


    @Transactional
    public TutorDetailingData update(Long id, TutorRegistrationData data) {
        var tutor = tutorRepository.getReferenceById(id);
        tutor.update(data);
        return new TutorDetailingData(tutor);
    }
}
