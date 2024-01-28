package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Etudiant")
public class Etudiant extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="IdEtudiant")
//    private long idEtudiant;
//
//    @Column(name= "nomEtudiant")
//    private String nomEtudiant;
//     @Column(name = "prenom")
//    private String prenom;
     @Column(name="cin")
    private long cin;
     @Column(name="ecole")
    private String ecole;

     @Temporal(TemporalType.DATE)
    private Date dateNaissance;
//    @Temporal(TemporalType.DATE)
//    private LocalDate dateEffet;


    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Reservation>reservations;
}
