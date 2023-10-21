package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;

import java.util.List;

public interface IEtudiantServices {
    List<Etudiant> addEtudiants(List<Etudiant> etudiants);
    Etudiant updateEtudiant(Etudiant e);
    List<Etudiant> retrieveAllEtudiants();
    Etudiant retrieveEtudiant(long  idEtudiant);
    void removeEtudiant(long idEtudiant);

}
