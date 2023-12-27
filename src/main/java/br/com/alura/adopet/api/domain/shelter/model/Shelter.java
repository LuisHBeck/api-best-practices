package br.com.alura.adopet.api.domain.shelter.model;

import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.domain.shelter.dto.ShelterRegistrationData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "shelters")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;

    public Shelter(ShelterRegistrationData data) {
        this.name = data.name();
        this.phone = data.phone();
        this.email = data.email();
    }
}
