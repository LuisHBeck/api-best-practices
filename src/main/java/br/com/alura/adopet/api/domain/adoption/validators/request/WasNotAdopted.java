package br.com.alura.adopet.api.domain.adoption.validators.request;

import br.com.alura.adopet.api.exception.validation.ValidException;
import br.com.alura.adopet.api.model.Adoption;
import org.springframework.stereotype.Component;

@Component
public class WasNotAdopted implements AdoptionRequestValidator{
    @Override
    public void validate(Adoption adoption) {
        if (adoption.getPet().getAdotado()) throw new ValidException("Dog already adopted!");
    }
}
