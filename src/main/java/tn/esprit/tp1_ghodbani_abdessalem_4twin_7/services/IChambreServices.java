package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IChambreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IChambreServices implements IChambreService {
    private final IChambreRepository chambreRepository;


    @Override
    public List<Chambre> retrieveAllChambres() {
        Iterable<Chambre> chambres = chambreRepository.findAll();
        List<Chambre> chambreList = new ArrayList<>();
        chambres.forEach(chambreList::add);
        return chambreList;
    }

    @Override
    public Chambre addChambre(Chambre c) {
        Chambre newChambre = chambreRepository.save(c);
        return newChambre;
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        Optional<Chambre> isExistingChambre=chambreRepository.findById(c.getIdChambre());
        if (isExistingChambre.isPresent()){
            Chambre chambreToUpdated=isExistingChambre.get();
            chambreToUpdated.setNumeroChambre(c.getNumeroChambre());
            chambreToUpdated.setTypeC(c.getTypeC());
            chambreToUpdated.setReservations(c.getReservations());
            chambreToUpdated.setBloc(c.getBloc());
            return chambreToUpdated;
        }
        else {
            throw new RessourceNotFound("Chambre avec id : " +c.getIdChambre() + "n'existe pas ");
        }
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
       Optional<Chambre>chambre=chambreRepository.findById(idChambre);
       if (chambre.isPresent()){
           Chambre chambreGetting=chambre.get();
           return chambreGetting;
       }
       else {
           throw new RessourceNotFound("Chambre n'existe pas avec l'id  " +idChambre );
       }
    }
}
