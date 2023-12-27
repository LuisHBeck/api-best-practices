package br.com.alura.adopet.api.domain.pet.model;

import br.com.alura.adopet.api.domain.adoption.model.Adoption;
import br.com.alura.adopet.api.domain.pet.dto.PetRegistrationData;
import br.com.alura.adopet.api.domain.shelter.model.Shelter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String name;

    private String race;

    private Integer age;

    private String color;

    private Float weight;

    private Boolean adopted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id", referencedColumnName = "id")
    private Shelter shelter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_id", referencedColumnName = "id")
    private Adoption adoption;

    public Pet(PetRegistrationData data, Shelter shelter) {
        this.type = data.type();
        this.name = data.name();
        this.race = data.race();
        this.age = data.age();
        this.color = data.color();
        this.weight = data.weight();
        this.adopted = false;
        this.shelter = shelter;
    }
}
