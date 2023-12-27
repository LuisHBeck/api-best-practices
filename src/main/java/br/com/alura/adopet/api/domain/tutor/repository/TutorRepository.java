package br.com.alura.adopet.api.domain.tutor.repository;

import br.com.alura.adopet.api.domain.tutor.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

}
