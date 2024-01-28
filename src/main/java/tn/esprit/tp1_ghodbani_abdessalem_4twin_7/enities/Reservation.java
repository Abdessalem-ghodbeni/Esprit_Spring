package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservation")
    private long idReservation;
    @Column(name = "numReservation")
    private  String numReservation;


    @Temporal(TemporalType.DATE)
    private LocalDate debutAnneeUniversitaire;
    @Temporal(TemporalType.DATE)
    private LocalDate finAnneUniversitaire;
    @Column(name="estValide")
     boolean estActive;
    @ManyToMany(  mappedBy = "reservations")
    private Set<Etudiant> etudiants;


}
