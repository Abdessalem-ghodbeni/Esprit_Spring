package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter
@Table(name = "Universite")
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUniversite")
    private long idUniversite;

    @Column(name = "nomUniversite",unique = true)
    private String nomUniversite;

    @Column(name = "adresse")
    private String adresse;
    @OneToOne
    @JsonIgnore
    private Foyer foyer;
}
