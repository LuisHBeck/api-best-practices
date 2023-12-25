package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdoptionRequestData(
        @NotNull
        @NotBlank
        @JsonAlias({"pet", "pet_id"})
        Long petId,

        @NotNull
        @NotBlank
        @JsonAlias({"tutor", "tutor_id"})
        Long tutorId,

        @NotNull
        @NotBlank
        String reason) {
}
