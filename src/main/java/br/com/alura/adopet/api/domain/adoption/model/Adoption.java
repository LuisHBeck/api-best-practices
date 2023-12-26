package br.com.alura.adopet.api.domain.adoption.model;

import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adoptions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;

    private String reason;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;

    private String statusJustification;

    public Adoption(Tutor tutor, Pet pet, String reason) {
        this.tutor = tutor;
        this.pet = pet;
        this.reason = reason;
        this.status = AdoptionStatus.AWAITING_EVALUATION;
        this.date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoption adoption = (Adoption) o;
        return Objects.equals(id, adoption.id);
    }

    public void approve() {
        this.status = AdoptionStatus.APPROVED;
    }

    public void disapprove() {
        this.status = AdoptionStatus.DISAPPROVED;
    }
}
