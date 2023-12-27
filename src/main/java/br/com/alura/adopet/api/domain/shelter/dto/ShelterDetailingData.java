package br.com.alura.adopet.api.domain.shelter.dto;

import br.com.alura.adopet.api.domain.shelter.model.Shelter;
import jakarta.validation.constraints.Pattern;

public record ShelterDetailingData(Long id, String name, String phone, String email) {

        public ShelterDetailingData(Shelter shelter) {
                this(shelter.getId(), shelter.getName(), shelter.getPhone(), shelter.getEmail());
        }
}
