package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IFoyerRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IUniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    final IFoyerRepository foyerRepository;
    final IUniversiteRepository universiteRepository;


    @Override
    public List<Foyer> retrieveAllFoyers() {
       List <Foyer> foyers= (List<Foyer>) foyerRepository.findAll();
       return  foyers;
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        Foyer nouveauFoyer = foyerRepository.save(f);
        return nouveauFoyer;
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        Foyer foyerById = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RessourceNotFound("foyer introuvable avec l'id : " + idFoyer));
        return foyerById;
    }


    @Override
    public void removeFoyer(long idFoyer) {
        Optional<Foyer> foyeeToDeletedExisting = foyerRepository.findById(idFoyer);
        if (foyeeToDeletedExisting.isPresent()) {
            foyerRepository.deleteById(idFoyer);
        } else {
            throw new RessourceNotFound("foyer non trouve avec id " + idFoyer);
        }

    }


    @Override
    public Foyer updateFoyer(Foyer f) {

        Optional<Foyer> exixstingFoyerToUpdated = foyerRepository.findById(f.getIdFoyer());
        if (exixstingFoyerToUpdated.isPresent()) {
            Foyer foyerReadyToUpdate = exixstingFoyerToUpdated.get();
            foyerReadyToUpdate.setNomFoyer(f.getNomFoyer());
            foyerReadyToUpdate.setCapaciteFoyer(f.getCapaciteFoyer());
            foyerReadyToUpdate.setUniversite(f.getUniversite());
            foyerReadyToUpdate.setBlocs(f.getBlocs());
            return foyerReadyToUpdate;
        } else {
            throw new RessourceNotFound("not found this foyer avec id " + f.getIdFoyer());
        }

    }


}
