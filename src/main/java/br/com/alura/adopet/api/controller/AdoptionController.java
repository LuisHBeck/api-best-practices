package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.domain.adoption.validators.request.AdoptionRequestValidator;
import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdoptionRepository;
import br.com.alura.adopet.api.service.AdoptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private AdoptionService adoptionService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> request(@RequestBody @Valid Adoption adoption) {
        adoptionService.request(adoption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/approve")
    @Transactional
    public ResponseEntity<String> approve(@RequestBody @Valid Adoption adoption) {
        adoptionService.approve(adoption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disapprove")
    @Transactional
    public ResponseEntity<String> disapprove(@RequestBody @Valid Adoption adoption) {
        adoptionService.disapprove(adoption);
        return ResponseEntity.ok().build();
    }

}
