package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
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
        List<Foyer> foyers = (List<Foyer>) foyerRepository.findAll();
        return foyers;
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
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
//        return foyerRepository.save(f);
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

//    @Override
//    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
//        Foyer foyer = foyerRepository.findById(idFoyer)
//                .orElseThrow(() -> new RessourceNotFound("Foyer non trouvé avec l'ID : " + idFoyer));
//        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
//        universite.setFoyer(foyer);
//        return universiteRepository.save(universite);
//    }

//    @Override
//    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
//        Foyer foyer = foyerRepository.findById(idFoyer).orElseThrow(() -> new RessourceNotFound("Foyer n'existe pas avec id " + idFoyer));
//        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new RessourceNotFound("Universite n'existe pas avec id :" + idUniversite));
//        if (universite.getFoyer() != null && universite.getFoyer().getIdFoyer() == idFoyer) {
//            universite.setFoyer(null);
//            return universiteRepository.save(universite);
//        } else {
//            throw new RessourceNotFound("Il n'existe pas de foyer affecté à cette universite avec cet id " + idFoyer);
//        }
//    }



}
