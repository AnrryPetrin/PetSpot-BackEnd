package br.com.petspot.controller;

import br.com.petspot.dto.petDto.RegisterPetDto;
import br.com.petspot.dto.petDto.SavedDatasPetDto;
import br.com.petspot.dto.petDto.UpdatePetDto;
import br.com.petspot.dto.petDto.UpdatePetWeightDto;
import br.com.petspot.model.Pet.Pet;
import br.com.petspot.model.petOwner.PetOwner;
import br.com.petspot.repository.PetOwnerRepository;
import br.com.petspot.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/petspot")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetOwnerRepository ownerRepository;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity registerPet(@RequestBody RegisterPetDto petDto, @PathVariable(name = "id") String param, UriComponentsBuilder uriBuilder) {
        PetOwner owner = ownerRepository.getReferenceById(param);

        Pet pet = new Pet(petDto);
        petRepository.save(pet);

        owner.getPet().add(pet);
        ownerRepository.save(owner);

        var uri = uriBuilder.path("/pet/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new SavedDatasPetDto(pet));
    }

    @GetMapping("/meuspets/{ownerId}")
    public ResponseEntity<List<SavedDatasPetDto>> getAllPetsByOwner(@PathVariable String ownerId) {
        PetOwner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        List<Pet> pets = new ArrayList<>(owner.getPet());
        List<SavedDatasPetDto> petDtos = pets.stream().map(pet -> new SavedDatasPetDto(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday())).collect(Collectors.toList());

        return ResponseEntity.ok(petDtos);
    }

    @GetMapping("/meuspets/buscarpet/{petName}")
    public ResponseEntity<List<SavedDatasPetDto>> getPetsByName(@PathVariable("petName") String petName) {
        System.out.println("Buscando pets com o nome: " + petName);

        List<Pet> pets = petRepository.findByPetNameContainingIgnoreCase(petName);

        if (pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SavedDatasPetDto> petDtos = pets.stream().map(pet -> new SavedDatasPetDto(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday())).collect(Collectors.toList());

        return ResponseEntity.ok(petDtos);
    }

    @PatchMapping("/meuspets/renomear/{petId}")
    public ResponseEntity<SavedDatasPetDto> renamePet(@PathVariable String petId, @RequestBody UpdatePetDto updatePetDto) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        pet.setPetName(updatePetDto.petName());
        petRepository.save(pet);

        SavedDatasPetDto responseDto = new SavedDatasPetDto(pet);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/meuspets/atualizarpeso/{petId}")
    public ResponseEntity<SavedDatasPetDto> updatePetWeight(@PathVariable String petId, @RequestBody UpdatePetWeightDto updatePetWeightDto) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found with id: " + petId));

        pet.setPetWeight(updatePetWeightDto.peso());
        petRepository.save(pet);

        SavedDatasPetDto responseDto = new SavedDatasPetDto(pet);
        return ResponseEntity.ok(responseDto);
    }
}