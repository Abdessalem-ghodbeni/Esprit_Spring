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
@Table(name="Etudiant")
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdEtudiant")
    private long IdEtudiant;

    @Column(name= "nomEtudiant")
    private String nomEtudiant;
     @Column(name = "prenom")
    private String prenom;
     @Column(name="cin")
    private long cin;
     @Column(name="ecole")
    private String ecole;

     @Temporal(TemporalType.DATE)
    private Date dateNaissance;
     @ManyToMany(mappedBy = "Etudiant")
    private Set<Etudiant> etudiants=new HashSet<>();

}
