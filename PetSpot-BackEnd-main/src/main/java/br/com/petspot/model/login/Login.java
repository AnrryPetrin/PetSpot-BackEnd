package br.com.petspot.model.login;

import br.com.petspot.model.petOwner.PetOwner;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import br.com.petspot.dto.registerDto.RegisterDto;
import java.util.UUID;

/**
 * @author Lincoln
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "email"})
@Entity(name = "Login")
@Table(name = "login")
public class Login {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    @Email
    private String email;

    private String passwordLogin;

    private String typeOfUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;

    public Login(RegisterDto registerDto) {
        this.email = registerDto.email();
        this.passwordLogin = registerDto.senha();
        this.typeOfUser = registerDto.usuario();
    }
}
