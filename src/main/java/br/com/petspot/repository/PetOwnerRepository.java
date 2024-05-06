package br.com.petspot.repository;

import br.com.petspot.model.petOwner.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetOwnerRepository extends JpaRepository<PetOwner, String> {
}
