package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@Table(name = "Foyer")
public class Foyer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "idFoyer")
    private long idFoyer;

    @Column(name = "nomFoyer")
    private String nomFoyer;
 @Column(name = "capaciteFoyer")
    private long capaciteFoyer;
 @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Universite_id")
    private Universite universite;
 @OneToMany(mappedBy = "Foyer")
    private List<Bloc> bloc;

}
