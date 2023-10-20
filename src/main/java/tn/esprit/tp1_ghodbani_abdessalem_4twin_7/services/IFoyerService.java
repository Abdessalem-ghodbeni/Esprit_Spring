package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer(Foyer NouveauFoyer);

    Foyer retrieveFoyer(long idFoyer);

    void removeFoyer(long idFoyer);

    Foyer updateFoyer(Foyer f);

}
