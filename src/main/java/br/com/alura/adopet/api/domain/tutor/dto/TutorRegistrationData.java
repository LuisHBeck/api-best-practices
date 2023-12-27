package br.com.alura.adopet.api.domain.tutor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorRegistrationData(
        @NotBlank
        @NotNull
        String name,

        @NotBlank
        @NotNull
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String phone,

        @Email
        @NotBlank
        @NotNull
        String email
) {
}
