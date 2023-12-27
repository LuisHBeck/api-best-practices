package br.com.alura.adopet.api.domain.pet.dto;

import br.com.alura.adopet.api.domain.pet.model.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRegistrationData(
        @NotBlank
        PetType type,

        @NotBlank
        @NotNull
        String name,

        @NotBlank
        @NotNull
        String race,

        @NotNull
        Integer age,

        @NotBlank
        @NotNull
        String color,

        @NotNull
        Float weight) {
}
