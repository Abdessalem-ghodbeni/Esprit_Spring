package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservation")
    private String idReservation;
@Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;
@Column(name="estValide")
    private boolean estActive;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Etudiant_reservation",
            joinColumns = @JoinColumn(name = "Etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "Reservation_id")
    )

    private Set<Reservation> reservation =new HashSet<>();
}
