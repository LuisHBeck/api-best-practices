package br.com.alura.adopet.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdoptionDisapprovalData(
        @NotNull
        @JsonAlias({"id", "adoption"})
        Long adoptionId,

        @NotNull
        @NotBlank
        @JsonAlias({"justification"})
        String disapprovalJustification) {
}
