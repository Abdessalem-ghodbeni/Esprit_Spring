package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c);
    Chambre updateChambre (Chambre c);
    Chambre retrieveChambre (long idChambre);
//    public Bloc affecterChambresABloc (List<String> numChambre, String nomBloc) ;


}
