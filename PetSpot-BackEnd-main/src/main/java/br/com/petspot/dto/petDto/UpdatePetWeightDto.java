package br.com.petspot.dto.petDto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePetWeightDto(
        @NotBlank(message = "Weight cannot be blank")
        String peso
) {}