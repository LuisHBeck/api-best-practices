package br.com.alura.adopet.api.domain.adoption.repository;

import br.com.alura.adopet.api.domain.adoption.model.Adoption;
import br.com.alura.adopet.api.domain.adoption.model.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    Boolean existsByPetIdAndStatus(Long petId, AdoptionStatus statusAdocao);

    Boolean existsByTutorIdAndStatus(Long tutorID, AdoptionStatus statusAdocao);

    Long countByTutorIdAndStatus(Long aLong, AdoptionStatus statusAdocao);
}
