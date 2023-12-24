package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdoptionRequestData(
        @NotNull
        @NotBlank
        Pet pet,

        @NotNull
        @NotBlank
        Tutor tutor,

        @NotNull
        @NotBlank
        String reason) {
}
