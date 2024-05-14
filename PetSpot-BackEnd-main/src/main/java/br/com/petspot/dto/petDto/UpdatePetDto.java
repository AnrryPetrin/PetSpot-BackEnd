package br.com.petspot.dto.petDto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePetDto(
        @NotBlank(message = "Pet name cannot be blank")
        String petName
) {}