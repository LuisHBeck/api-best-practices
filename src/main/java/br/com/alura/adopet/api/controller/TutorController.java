package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.domain.tutor.dto.TutorDetailingData;
import br.com.alura.adopet.api.domain.tutor.dto.TutorRegistrationData;
import br.com.alura.adopet.api.domain.tutor.model.Tutor;
import br.com.alura.adopet.api.domain.tutor.repository.TutorRepository;
import br.com.alura.adopet.api.domain.tutor.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutors")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid TutorRegistrationData data) {
        var tutor = tutorService.register(data);
        return ResponseEntity.ok(tutor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid TutorRegistrationData data) {
        var tutor = tutorService.update(id, data);
        return ResponseEntity.ok(tutor);
    }

}
