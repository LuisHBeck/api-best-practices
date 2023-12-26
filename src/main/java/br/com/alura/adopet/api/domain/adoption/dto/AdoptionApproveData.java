package br.com.alura.adopet.api.domain.adoption.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AdoptionApproveData(
        @NotNull
        @JsonAlias({"id", "adoption"})
        Long adoptionId) {
}
