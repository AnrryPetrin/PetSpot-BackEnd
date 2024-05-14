package br.com.petspot.model.Pet;

import br.com.petspot.dto.petDto.RegisterPetDto;
import br.com.petspot.model.petOwner.PetOwner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pet {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String id;

    private String petName;

    private String petWeight;

    private Date petBirthday;

    private String specie;

    private String race;

    private Integer gender;

    @JsonIgnore
    @ManyToMany(mappedBy = "pet")
    private Set<PetOwner> petOwners = new HashSet<>();

    public Pet(RegisterPetDto petDto) {
        this.petName = petDto.nome();

        this.specie = petDto.especie();

        if (petDto.genero() != null) {
            this.gender = petDto.genero();
        }
        if (petDto.dataDeNascimento() != null) {
            this.petBirthday = petDto.getDate();
        }
        if (petDto.peso() != null) {
            this.petWeight = petDto.peso();
        }
        if (petDto.raca() != null) {
            this.race = petDto.raca();
        }
    }
}
