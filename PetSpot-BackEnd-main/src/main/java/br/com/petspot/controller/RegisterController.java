package br.com.petspot.controller;

import br.com.petspot.dto.loginDto.EmailDto;
import br.com.petspot.dto.registerDto.RegisterDto;
import br.com.petspot.model.login.Login;
import br.com.petspot.model.petOwner.PetOwner;
import br.com.petspot.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.petspot.dto.registerDto.RegisterDto;


@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    private ResponseEntity register(@RequestBody @Validated RegisterDto registerDto, UriComponentsBuilder uriBuilder){
        Login login = new Login(registerDto);
        PetOwner petOwner = new PetOwner(registerDto);

        login.setPetOwner(petOwner);

        loginRepository.save(login);

        var uri = uriBuilder.path("/profile/{id}").buildAndExpand(login.getId()).toUri();

        return ResponseEntity.created(uri).body(new EmailDto(login.getEmail()));
    }

}
