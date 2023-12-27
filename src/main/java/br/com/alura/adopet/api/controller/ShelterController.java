package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.domain.pet.dto.PetDetailingData;
import br.com.alura.adopet.api.domain.pet.dto.PetRegistrationData;
import br.com.alura.adopet.api.domain.shelter.dto.ShelterDetailingData;
import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import br.com.alura.adopet.api.domain.shelter.model.Shelter;
import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.domain.shelter.repository.ShelterRepository;
import br.com.alura.adopet.api.domain.shelter.service.ShelterService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/shelters")
public class ShelterController {

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public ResponseEntity<Page<ShelterDetailingData>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = shelterService.listAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ShelterRegistrationData data, UriComponentsBuilder uriBuilder) {
        var shelter = shelterService.register(data);
        var uri = uriBuilder.path("/shelters/{id}").build(shelter.id());

        return ResponseEntity.created(uri).body(shelter);
    }

    @GetMapping("/{idOrName}/pets")
    public ResponseEntity<List<PetDetailingData>> listPets(@PathVariable String idOrName) {
        List<PetDetailingData> petList = shelterService.listPets(idOrName);
        return ResponseEntity.ok(petList);
    }

    @PostMapping("/{idOrName}/pets")
    @Transactional
    public ResponseEntity registerPet(@PathVariable String idOrName, @RequestBody @Valid PetRegistrationData data) {
        var pet = shelterService.registerPet(idOrName, data);
        return ResponseEntity.ok(pet);
    }

}
