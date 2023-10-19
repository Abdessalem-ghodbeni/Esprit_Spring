package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return foyerRepository.save(f);
    }

    @Override
    public void supprimerFoyer(long idFoyer) {
foyerRepository.deleteById(idFoyer);
    }

    @Override
    public Foyer getFoyer(long idFoyer) {
       return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public List<Foyer> getAllFoyer() {
        return (List<Foyer>)  foyerRepository.findAll() ;
    }
}
