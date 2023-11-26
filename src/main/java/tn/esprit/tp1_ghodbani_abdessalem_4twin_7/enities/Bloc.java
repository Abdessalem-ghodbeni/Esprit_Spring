package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "idBloc")
    private long idBloc;

    @Column(name = "nomBloc")
    private String nomBloc;
    @Column(name = "capaciteBloc")
    private String capaciteBloc;
@JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "bloc")
    private Set<Chambre> chambres;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Foyer foyer;


}
