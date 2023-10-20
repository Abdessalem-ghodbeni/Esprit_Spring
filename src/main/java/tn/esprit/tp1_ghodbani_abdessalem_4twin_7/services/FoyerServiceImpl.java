package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IFoyerRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IUniversiteRepository;

import java.util.List;
 @Service
@RequiredArgsConstructor
public class FoyerServiceImpl implements IFoyerService{

   final IFoyerRepository foyerRepository;
     final IUniversiteRepository universiteRepository;



    @Override
    public Foyer ajouterFoyer(Foyer f) {
        Foyer nouveauFoyer= foyerRepository.save(f);
        return nouveauFoyer;
    }

     @Override
     public Foyer getFoyer(long idFoyer) {
        Foyer foyerById= foyerRepository.findById(idFoyer)
                .orElseThrow(()->new RessourceNotFound("foyer introuvable avec l'id : "+idFoyer));
        return foyerById;
     }

     @Override
     public List<Foyer> getAllFoyer() {
         List<Foyer> foyers= (List<Foyer>) foyerRepository.findAll();
         return foyers;

     }
     @Override
    public void supprimerFoyer(long idFoyer) {
Foyer foyeeToDeleted=foyerRepository.findById(idFoyer).orElseThrow(()->new RessourceNotFound("Foyer NON TROUVABLE AVEC L'ID"+idFoyer));
foyerRepository.deleteById(idFoyer);

    }

     @Override
     public Foyer UpdateFoyer(long id_foyer) {
         return null;
     }


 }
