package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.domain.pet.dto.PetDetailingData;
import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.domain.pet.repository.PetRepository;
import br.com.alura.adopet.api.domain.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<Page<PetDetailingData>> listAllAvailable(@PageableDefault(size = 10, sort = {"type"}) Pageable pageable) {
        var page = petService.listAvailable(pageable);
        return ResponseEntity.ok(page);
    }

}
