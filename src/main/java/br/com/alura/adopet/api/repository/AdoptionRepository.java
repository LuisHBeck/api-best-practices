package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adoption;
import br.com.alura.adopet.api.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    Boolean existsByPetIdAndStatus(Long petId, StatusAdocao statusAdocao);

    Boolean existsByTutorIdAndStatus(Long tutorID, StatusAdocao statusAdocao);

    Long countByTutorIdAndStatus(Long aLong, StatusAdocao statusAdocao);
}
