package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.FoyerServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/logement")
public class FoyerController {
    private final FoyerServiceImpl foyerService;

    @PostMapping(path = "/add_foyer")
    public ResponseEntity<Foyer> AddFoyer(@RequestBody Foyer NouveauFoyer) {
        Foyer newFoyer = foyerService.addFoyer(NouveauFoyer);
        return new ResponseEntity<>(newFoyer, HttpStatus.CREATED);

    }

    @GetMapping(path = "/get/foyer/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable("id") long FoyerId) {

        Foyer foyerDemande = foyerService.retrieveFoyer(FoyerId);
        return ResponseEntity.ok(foyerDemande);
    }

    @GetMapping(path = "/get/all_foyes")
    public ResponseEntity<List<Foyer>> getAllFoyer() {
        List<Foyer> foyers = foyerService.retrieveAllFoyers();
        return ResponseEntity.ok(foyers);


    }


    @PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierFoyer(@RequestBody Foyer f) {
        try {
            Foyer updatedFoyer = foyerService.updateFoyer(f);
            return new ResponseEntity<>(updatedFoyer, HttpStatus.OK);
        } catch (RessourceNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/supprimer/foyer/{id}")
    public ResponseEntity<String> SupprimerFoyer(@PathVariable("id") long foyerId) {
        try {
            foyerService.removeFoyer(foyerId);
            return ResponseEntity.ok("Foyer deleted Successfuly");
        } catch (RessourceNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ce foyer est introuvable avec id " + foyerId);
        }


    }


//    @PutMapping(path = "/{idFoyer}/affecter/{nomUniversite}")
//    public ResponseEntity<Universite> affecterFoyer(@PathVariable("idFoyer") long idFoyer, @PathVariable("nomUniversite") String nomUniversite) {
//        Universite universiteAffecte = foyerService.affecterFoyerAUniversite(idFoyer, nomUniversite);
//        return ResponseEntity.ok().body(universiteAffecte);
//    }

//    @PutMapping(path = "/{idFoyer}/desaffecter/{idUniversity}")
//    public ResponseEntity<Universite> desaffecterFoyer(@PathVariable("idFoyer") long idFoyer, @PathVariable("idUniversity") long idUniversity) {
//        Universite university = foyerService.desaffecterFoyerAUniversite(idFoyer, idUniversity);
//        return ResponseEntity.ok(university);
//    }
    @PutMapping(path="/addAndAffecte/{idUniversity}")
    public ResponseEntity<?> ajouterFoyerAffectantUniversity(@PathVariable("idUniversity") Long idUniversity ,@RequestBody Foyer foyer){

       try{
           Foyer foyerAdded=foyerService.ajouterFoyerEtAffecterUniversite(foyer, idUniversity);
           return new ResponseEntity<>(foyerAdded,HttpStatus.CREATED);
       }
       catch(RessourceNotFound exception){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }


    }

}
