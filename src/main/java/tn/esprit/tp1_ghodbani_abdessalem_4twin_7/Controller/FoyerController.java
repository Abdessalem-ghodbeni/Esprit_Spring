package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
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
//        try {
        List<Foyer> foyers = foyerService.retrieveAllFoyers();
        return ResponseEntity.ok(foyers);
//        }
//        catch (RessourceNotFound e ){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }

    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierFoyer(@RequestBody Foyer f) {
        try {
            Foyer updatedFoyer = foyerService.updateFoyer(f);
            if (updatedFoyer == null) {
                return new ResponseEntity<>("Foyer non trouvable", HttpStatus.NOT_FOUND);
            }
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


}
