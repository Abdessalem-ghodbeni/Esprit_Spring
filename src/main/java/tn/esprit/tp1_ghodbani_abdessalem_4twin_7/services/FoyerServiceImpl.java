package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IBlocRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IFoyerRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IUniversiteRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    final IFoyerRepository foyerRepository;
    final IUniversiteRepository universiteRepository;
    final IBlocRepository blocRepository;


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
//        return foyerRepository.save(f); avec cette methode meme si le foyer n'xiste pas il va le crrer
        Optional<Foyer> exixstingFoyerToUpdated = foyerRepository.findById(f.getIdFoyer());
        if (exixstingFoyerToUpdated.isPresent()) {
            Foyer foyerReadyToUpdate = exixstingFoyerToUpdated.get();
            foyerReadyToUpdate.setNomFoyer(f.getNomFoyer());
            foyerReadyToUpdate.setCapaciteFoyer(f.getCapaciteFoyer());
//            foyerReadyToUpdate.setUniversite(f.getUniversite());
            if (f.getUniversite() != null) {
                foyerReadyToUpdate.setUniversite(f.getUniversite());
            }

            // Mise à jour des blocs si la liste n'est pas vide
            if (f.getBlocs() != null && !f.getBlocs().isEmpty()) {
                foyerReadyToUpdate.setBlocs(f.getBlocs());
            }
//            foyerReadyToUpdate.setBlocs(f.getBlocs());
            return foyerRepository.save(foyerReadyToUpdate);
        } else {
            throw new RessourceNotFound("not found this foyer avec id " + f.getIdFoyer());
        }}
    @Transactional
    @Override
    public Foyer ajouterFoyerEtAffecterUniversite(Foyer f, Long idUniversity) {

      Universite universite=universiteRepository.findById(idUniversity).orElseThrow(()->new RessourceNotFound("id introuvable"+idUniversity));
       universite.setFoyer(f);
       for (Bloc aBloc:f.getBlocs()){
           aBloc.setFoyer(f);
           blocRepository.save(aBloc);
       } return f;
    }


}

