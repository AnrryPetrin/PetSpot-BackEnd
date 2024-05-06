package br.com.petspot.dto.loginDto;

import jakarta.validation.constraints.Email;

/**
 * @author Lincoln
 */
public record LoginDto(
        @Email
        String email,
        String senha
) {
}
