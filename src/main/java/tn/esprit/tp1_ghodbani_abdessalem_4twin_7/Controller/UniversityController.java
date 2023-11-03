package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "University")
public class UniversityController {
    private final UniversityServiceImp universityService;


    @Operation(
            description = "c'est l'api d'ajout une université a nitre base de données",
            summary = "notre fameuse api permet l'ajout et le sauvgardage d'une nouvelle univeristé dans la base de données ",
            responses = {
                    @ApiResponse(
                            description = "success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! ",
                            responseCode = "404"
                    )

            }
    )
    @PostMapping(path = "/add")
    public ResponseEntity<?> ajouterUniversity(@RequestBody Universite universitty) {
        try {
            Universite newUniversite = universityService.addUniversity(universitty);
            return new ResponseEntity<>(newUniversite, HttpStatus.CREATED);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @Operation(
            description = "cet api nous permet de modifier une université deja existante",
            summary = "notre fameuse api prend un objet de type université faire extraire l'id de cet objet puis faire chercher avec cet id une univeristé dans la base de données , si c'est le cas ou il existe une université avec cet id alors l'opration de modification est effectuée si non elle est failed car pas d'université a modifier",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! id introuvable ",
                            responseCode ="404"
                    )
            }
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<?> UpdatingUniversity(@RequestBody Universite university) {
        try {
            Universite universiteUpdate = universityService.updateUniversity(university);
            return new ResponseEntity<>(universiteUpdate, HttpStatus.OK);

        } catch (RessourceNotFound Exception) {
            return new ResponseEntity<>(Exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            description = "Récuperer une université a partir d'un id donnée",
            summary = "notre fameuse api prend en parametre un id, elle cherche une université avec cet id là dans la base de  donnée si c'est le cas elle la renvoie directement comme reponse a notre demande si non elle renvoie erreur",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! introuvable university avec cet id ",
                            responseCode = "404"
                    )

            }


    )
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

    @Operation(
            description = "cet api nous permet de modifier une université deja existante",
            summary = "notre fameuse api a comme objectif de faire retourner tous les université sauvgarder dans notre base de données",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! id introuvable ",
                            responseCode ="404"
                    )
            }
    )
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

    @Operation(
            description = "cet api nous permet de supprimer une université ",
            summary = "notre fameuse api prend un id   puis faire chercher avec cet id une univeristé dans la base de données , si c'est le cas ou il existe une université avec cet id alors l'opration de suppression est effectuée si non elle est failed car pas d'université a supprimer",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! id introuvable ",
                            responseCode ="404"
                    )
            }
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable("id") long idUniversity) {
        try {
            universityService.removeUniversity(idUniversity);
            return ResponseEntity.ok("Univeristy deleted successfully");
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("university with id" + idUniversity + "n'existe pas ");
        }
    }

    @Operation(
            description = "cet api nous permet d'affecter un foyer a une université ",
            summary = "notre fameuse api prend deux paramétre nom université et idFoyer faire chercher le foyer avec id specifié si il esxite alors on passe a chercher université par nom et par suite on affecter ce foyer a cette université ",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed !",
                            responseCode ="404"
                    )
            }
    )
    @PutMapping("affecterFoyerUniversity/{idFoyer}/{nomUniversity}")
    public ResponseEntity<?> affecterFoyerForUniversity(@PathVariable("idFoyer") long idFoyer, @PathVariable("nomUniversity") String nomUniversity) {
        try {
            Universite universite = universityService.affecterFoyerAUniversite(idFoyer, nomUniversity);
            return new ResponseEntity<>(universite, HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

    @Operation(
            description = "cet api nous permet de desaffecter un foyer à une université",
            summary = "notre fameuse api prend deux paramétre id université et idFoyer faire chercher le foyer avec id specifié si il esxite alors on passe a chercher université par son id et par suite on désaffecter ce foyer a cette université",
            responses = {
                    @ApiResponse(
                            description = "succes",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! id introuvable ",
                            responseCode ="404"
                    )
            }
    )
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
