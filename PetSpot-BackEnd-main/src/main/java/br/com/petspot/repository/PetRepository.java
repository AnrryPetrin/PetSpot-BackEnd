package br.com.petspot.repository;

import br.com.petspot.model.Pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, String> {
    List<Pet> findByPetNameContainingIgnoreCase(String petName);
}

