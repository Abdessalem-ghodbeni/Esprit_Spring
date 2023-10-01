package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Getter
@Setter
@Table(name = "Chambre")
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idChambre")
    private long idChambre;

    @Column(name="numeroChambre")
    private long numeroChambre;
@Column(name = "TypeC")
    private TypeChambre TypeC;
@ManyToOne
    @JoinColumn(name = "Bloc_id")
    private Bloc bloc;
}
