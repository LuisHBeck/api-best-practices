package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.dto.AdoptionRequestData;
import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoesNotHaveAdoptionStatus implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validate(AdoptionRequestData adoptionRequestData) {
        var adoption = adoptionRepository.getReferenceById(adoptionRequestData.pet().getId());
        var tutor = tutorRepository.getReferenceById(adoptionRequestData.tutor().getId());
        List<Adoption> adoptions = adoptionRepository.findAll();
        for (Adoption a : adoptions) {
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidException("Tutor is already awaiting an adoption evaluation");
            }
        }
        for (Adoption a : adoptions) {
            if (a.getPet() == adoption.getPet() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidException("Dog is already awaiting an adoption evaluation to be adopted");
            }
        }
        for (Adoption a : adoptions) {
            int counter = 0;
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                counter = counter + 1;
            }
            if (counter == 5) {
                throw new ValidException("Tutor has reached the maximum limit of 5 adoptions");
            }
        }
    }
}
