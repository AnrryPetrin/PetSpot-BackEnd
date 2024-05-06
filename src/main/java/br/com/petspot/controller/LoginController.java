package br.com.petspot.controller;

import br.com.petspot.dto.loginDto.LoginDto;
import br.com.petspot.model.login.Login;
import br.com.petspot.repository.LoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lincoln
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginRepository repository;

    @PostMapping
    public ResponseEntity signIn(@RequestBody @Valid LoginDto loginDto) {

        Login auth = repository.findByEmailAndPasswordLogin(loginDto.email(), loginDto.senha());

        if (auth != null){
            return ResponseEntity.ok("Autenticação bem-sucedida.");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Autenticação falhou");
    }
}
