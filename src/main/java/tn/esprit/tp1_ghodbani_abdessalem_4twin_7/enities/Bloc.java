package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@ManyToOne
    @JoinColumn(name = "Foyer_id")
    private Bloc bloc;
@OneToMany(mappedBy = "bloc")
    private List<Chambre> chambres=new ArrayList<>();;

}
