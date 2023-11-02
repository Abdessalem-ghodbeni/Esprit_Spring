package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IFoyerRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IUniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UniversityServiceImp implements IUniversityService {
    private final IUniversiteRepository universiteRepository;
    private final IFoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        // ici marche convenablment ssi IUniversityRepository herite de JpaRepository mais si elle herite de
        // crudRepository ca marche pas car la methode retourne une itérable et non pas une liste je vais vous montrer comment ca marche avec CrudRepository
//        List<Universite> universiteList=universiteRepository.findAll();
//        return universiteList;

        Iterable<Universite> universiteIterable = universiteRepository.findAll();
        List<Universite> universiteList = new ArrayList<>();
        universiteIterable.forEach(universiteList::add);
        return universiteList;
    }

    @Override
    public Universite addUniversity(Universite u) {
        try {
            Universite newUniversity = universiteRepository.save(u);
            if (newUniversity == null) {
                throw new RessourceNotFound("une chose mal passée,Université n'a pas ete ajouté correctement  ");
            }
            return newUniversity;

        } catch (RessourceNotFound excep) {
            throw new RessourceNotFound("Erreur lors d'ajout d'université merci de verifier : " + excep.getMessage());
        }

    }

    @Override
    public Universite updateUniversity(Universite u) {
        Optional<Universite> existinInDb = universiteRepository.findById(u.getIdUniversite());
        if (existinInDb.isPresent()) {
            Universite universiteUpdated = existinInDb.get();
            universiteUpdated.setNomUniversite(u.getNomUniversite());
            universiteUpdated.setAdresse(u.getAdresse());
            return universiteUpdated;
        } else {
            throw new RessourceNotFound("university avec id" + u.getIdUniversite() + "n'existe pas ");
        }
    }

    @Override
    public Universite retrieveUniversity(long idUniversity) {
        Universite universite = universiteRepository.findById(idUniversity).orElseThrow(() -> new RessourceNotFound("pas d'université avec l'id spécifié" + idUniversity));
        return universite;
    }

    @Override
    public void removeUniversity(long idUniversity) {
        Optional<Universite> isHere = universiteRepository.findById(idUniversity);
        if (isHere.isPresent()) {
            Universite universiteDeleting = isHere.get();
            universiteRepository.delete(universiteDeleting);
        } else {
            throw new RessourceNotFound("Universite avec id : " + idUniversity + "n'existe pas ");
        }
    }
    @Transactional
    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer =foyerRepository.findById(idFoyer).orElseThrow(()->new RessourceNotFound("foyer non trouvable avec l'id"+idFoyer));
        Universite universite=universiteRepository.findByNomUniversite(nomUniversite);
        universite.setFoyer(foyer);
        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Foyer foyer=foyerRepository.findById(idFoyer).orElseThrow(()->new RessourceNotFound("foyer non trouvable avec l'id "+idFoyer));
        Universite universite=universiteRepository.findById(idUniversite).orElseThrow(()->new RessourceNotFound("université introuvable avec l'id"+idUniversite));
        if(universite.getFoyer()!=null && universite.getFoyer().getIdFoyer()==idFoyer){
            universite.setFoyer(null);
            return universiteRepository.save(universite);
        }else {
            throw new RessourceNotFound("accun foyer avec l'id"+idFoyer +" est associé a cette université ");
        }
    }
}
