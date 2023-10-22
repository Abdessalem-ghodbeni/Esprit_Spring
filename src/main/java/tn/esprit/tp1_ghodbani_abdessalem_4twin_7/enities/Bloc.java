package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Bloc")
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    private long idBloc;

    @Column(name = "nomBloc")
    private String nomBloc;
    @Column(name = "capaciteBloc")
    private String capaciteBloc;

@OneToMany(cascade = CascadeType.ALL,mappedBy ="bloc")
private Set<Chambre> chambres;
@ManyToOne
    private Foyer foyer;

//    public void addChambre(String numeroChambre) {
//        Chambre chambre = new Chambre();
//        chambre.setNumeroChambre(Long.parseLong(numeroChambre)); // definir le num   chambre pour cette instance de Chambre
//        this.chambres.add(chambre);
//    }

}
