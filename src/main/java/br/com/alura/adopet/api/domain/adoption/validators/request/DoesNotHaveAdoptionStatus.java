package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoesNotHaveAdoptionStatus implements AdoptionRequestValidator{

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public void validate(Adoption adoption) {
        List<Adoption> adoptions = adoptionRepository.findAll();
        for (Adoption a : adoptions) {
            if (a.getTutor() == adoption.getTutor() && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
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
            if (a.getTutor() == adoption.getTutor() && a.getStatus() == StatusAdocao.APROVADO) {
                counter = counter + 1;
            }
            if (counter == 5) {
                throw new ValidException("Tutor has reached the maximum limit of 5 adoptions");
            }
        }
    }
}
