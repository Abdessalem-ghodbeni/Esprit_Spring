package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.UniversityServiceImp;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/university")
public class UniversityController {
    private final UniversityServiceImp universityService;

    @PostMapping(path = "/add")
    public ResponseEntity<?> ajouterUniversity(@RequestBody Universite universitty) {
        try {
            Universite newUniversite = universityService.addUniversity(universitty);
            return new ResponseEntity<>(newUniversite, HttpStatus.CREATED);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> UpdatingUniversity(@RequestBody Universite university) {
        try {
            Universite universiteUpdate = universityService.updateUniversity(university);
            return new ResponseEntity<>(universiteUpdate, HttpStatus.OK);

        } catch (RessourceNotFound Exception) {
            return new ResponseEntity<>(Exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/recuperer/{id}")
    public ResponseEntity<?> GetUniversityById(@PathVariable("id") long idUniversity) {
        try {
            Universite universiteGetting = universityService.retrieveUniversity(idUniversity);
            if (universiteGetting == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Accune université avec cet id :" + idUniversity);
            }
            return ResponseEntity.ok(universiteGetting);
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("une chose mal passée");
        }
    }


    @GetMapping(path = "all_university")
    public ResponseEntity<?> GetALLUniversity() {
        try {
            List<Universite> universites = universityService.retrieveAllUniversities();
            if (universites.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("la liste des universite est vide ");
            }
            return ResponseEntity.ok(universites);
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("une chose mal passé");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable("id") long idUniversity) {
        try {
            universityService.removeUniversity(idUniversity);
            return ResponseEntity.ok("Univeristy deleted successfully");
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("university with id" + idUniversity + "n'existe pas ");
        }
    }


    @PutMapping("affecterFoyerUniversity/{idFoyer}/{nomUniversity}")
    public ResponseEntity<?> affecterFoyerForUniversity(@PathVariable("idFoyer") long idFoyer, @PathVariable("nomUniversity") String nomUniversity) {
        try {
            Universite universite = universityService.affecterFoyerAUniversite(idFoyer, nomUniversity);
            return new ResponseEntity<>(universite, HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }


    @PutMapping("desaffecter_foyer_from_university/{idFoyer}/{idUniversity}")
    public ResponseEntity<?> desaffecterFoyerFromUniversity(@PathVariable("idFoyer")long idFoyer,@PathVariable("idUniversity")long idUniversity){
        try{
            Universite universite=universityService.desaffecterFoyerAUniversite(idFoyer,idUniversity);
            return new ResponseEntity<>(universite,HttpStatus.OK);
        }
        catch (RessourceNotFound Exception){
            return new ResponseEntity<>(Exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
