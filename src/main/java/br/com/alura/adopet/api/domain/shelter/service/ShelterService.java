package br.com.alura.adopet.api.domain.shelter.service;

import br.com.alura.adopet.api.domain.pet.dto.PetDetailingData;
import br.com.alura.adopet.api.domain.pet.dto.PetRegistrationData;
import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.domain.pet.repository.PetRepository;
import br.com.alura.adopet.api.domain.shelter.dto.ShelterDetailingData;
import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import br.com.alura.adopet.api.domain.shelter.model.Shelter;
import br.com.alura.adopet.api.domain.shelter.repository.ShelterRepository;
import br.com.alura.adopet.api.domain.shelter.service.validation.registration.ShelterRegistrationValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ShelterRegistrationValidator> registrationValidators;


    public Page<ShelterDetailingData> listAll(Pageable pageable) {
        return shelterRepository.findAll(pageable).map(ShelterDetailingData::new);
    }

    @Transactional
    public ShelterDetailingData register(ShelterRegistrationData data) {
        registrationValidators.forEach(v -> v.validate(data));

        var shelter = new Shelter(data);
        shelterRepository.save(shelter);

        return new ShelterDetailingData(shelter);
    }

    public List<PetDetailingData> listPets(String idOrName) {
        var isOnlyDigits = onlyDigitsVerifier(idOrName);
        List<Pet> petList;

        if(isOnlyDigits) {
            var id = Long.parseLong(idOrName);
            petList = shelterRepository.getReferenceById(id).getPets();
        }else {
            var name = idOrName;
            petList = shelterRepository.findByName(name).getPets();
        }
        return petList.stream().map(PetDetailingData::new).collect(Collectors.toList());
    }

    @Transactional
    public PetDetailingData registerPet(String idOrName, PetRegistrationData petData) {
        var isOnlyDigits = onlyDigitsVerifier(idOrName);
        Shelter shelter;

        if(isOnlyDigits) {
            var id = Long.parseLong(idOrName);
            shelter = shelterRepository.getReferenceById(id);
        }else {
            var name = idOrName;
            shelter = shelterRepository.findByName(name);
        }

        Pet newPet = new Pet(petData, shelter);
        petRepository.save(newPet);

        shelter.getPets().add(newPet);
        shelterRepository.save(shelter); // this is really necessary??

        return new PetDetailingData(newPet);

    }

    private boolean onlyDigitsVerifier(String string) {
        return string.chars().allMatch(Character::isDigit);
    }
}
