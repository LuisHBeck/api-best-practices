package br.com.alura.adopet.api.domain.tutor.model;

import br.com.alura.adopet.api.domain.adoption.model.Adoption;
import br.com.alura.adopet.api.domain.tutor.dto.TutorRegistrationData;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tutors")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Adoption> adoptions;

    public Tutor(TutorRegistrationData data) {
        this.name = data.name();
        this.phone = data.phone();
        this.email = data.phone();
    }

    public void update(TutorRegistrationData data) {
        this.name = data.name();
        this.phone = data.phone();
        this.email = data.phone();
    }
}
