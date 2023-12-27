package br.com.alura.adopet.api.domain.shelter.repository;

import br.com.alura.adopet.api.domain.shelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    boolean existsByName(String nome);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    Shelter findByName(String name);
}
