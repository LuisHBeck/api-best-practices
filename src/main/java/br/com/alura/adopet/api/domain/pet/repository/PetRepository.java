package br.com.alura.adopet.api.domain.pet.repository;

import br.com.alura.adopet.api.domain.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
