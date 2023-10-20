package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer ajouterFoyer(Foyer NouveauFoyer);
    Foyer getFoyer(long idFoyer);
    List<Foyer> getAllFoyer();
    void supprimerFoyer(long idFoyer);
Foyer UpdateFoyer(long  id_foyer);

}
