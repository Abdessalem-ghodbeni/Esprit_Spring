package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.EtudiantServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/etudiant")
public class EtudiantController {
    private final EtudiantServiceImp etudiantService;

    @PostMapping(path = "/add_students")
    public ResponseEntity<List<Etudiant>> AjouterEtudiant(@RequestBody List<Etudiant> etudiants) {
        List<Etudiant> etudiantList = etudiantService.addEtudiants(etudiants);
        return new ResponseEntity<>(etudiantList, HttpStatus.CREATED);


    }

    @GetMapping(path = "/liste_students")
    public ResponseEntity<List<Etudiant>> getAllEtudiant() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    @PutMapping(path = "/edit_student")
    public ResponseEntity<?> ModifierEtudiant(@RequestBody Etudiant e) {
        try {
            Etudiant etudiantUpdate = etudiantService.updateEtudiant(e);
            if (etudiantUpdate == null) {
                return new ResponseEntity<>("Etudiant non trouvale", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(etudiantUpdate, HttpStatus.OK);
        } catch (RessourceNotFound exception) {

            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path="/recupere/student/{id}")
    public ResponseEntity<Etudiant>GetEtudiant(@PathVariable("id") long idEtudiant){
        Etudiant student=etudiantService.retrieveEtudiant(idEtudiant);
        return ResponseEntity.ok(student);

    }


    @DeleteMapping(path = "/delete_student/{id}")
    public ResponseEntity<String> SupprimerStudents(@PathVariable("id") long idStudent){
     try {
         etudiantService.removeEtudiant(idStudent);
         return ResponseEntity.ok("Etudiant deleted Successfuly");
     }
     catch (RessourceNotFound exception) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Etudiant n'existe pas avec id "+idStudent);
     }

     }
    }




