package br.com.alura.adopet.api.domain.pet.service;

import br.com.alura.adopet.api.domain.pet.dto.PetDetailingData;
import br.com.alura.adopet.api.domain.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;

    public Page<PetDetailingData> listAvailable(Pageable pageable) {
        var page = petRepository.findAllByAdoptedFalse(pageable).map(PetDetailingData::new);
        return page;
    }
}
