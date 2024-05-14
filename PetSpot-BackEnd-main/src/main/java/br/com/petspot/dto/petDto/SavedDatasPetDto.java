package br.com.petspot.dto.petDto;

import br.com.petspot.model.Pet.Pet;

import java.util.Date;

public record SavedDatasPetDto(
        String id,
        String nome,
        int genero,
        Date idade
) {
    public SavedDatasPetDto(Pet pet){
        this(pet.getId(), pet.getPetName(), pet.getGender(), pet.getPetBirthday());
    }
}
