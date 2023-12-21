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
    private JavaMailSender emailSender;

    @Autowired
    private AdoptionService adoptionService;

    @Autowired
    private List<AdoptionRequestValidator> requestValidators;

    @PostMapping
    @Transactional
    public ResponseEntity<String> request(@RequestBody @Valid Adoption adoption) {
        requestValidators.forEach(v -> v.validate(adoption));
        adoptionService.request(adoption);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid Adoption adocao) {
        adocao.setStatus(StatusAdocao.APROVADO);
        adoptionRepository.save(adocao);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(adocao.getTutor().getEmail());
        email.setSubject("Adoção aprovada");
        email.setText("Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
        emailSender.send(email);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid Adoption adocao) {
        adocao.setStatus(StatusAdocao.REPROVADO);
        adoptionRepository.save(adocao);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(adocao.getTutor().getEmail());
        email.setSubject("Adoção reprovada");
        email.setText("Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());
        emailSender.send(email);

        return ResponseEntity.ok().build();
    }

}
