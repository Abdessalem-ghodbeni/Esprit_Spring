package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.BlocServiceImp;

import java.security.PublicKey;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(path = "/bloc")
@Tag(name = "Bloc")
public class BlocController {

    private final BlocServiceImp blocService;

@Operation(
        description = "c'est le service Api qui permet de recuperer tous les bloc dans notre base de données ... vous etes les bienvenus",
        summary = "en effet notre fameuse methode renvoie une liste contient tous les bloc enregistrés dans la base de données",
        responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200"
                ),
                @ApiResponse(
                        description = "failed ! liste vide ",
                        responseCode = "404"
                )
        }
)
    @GetMapping(path = "/all/blocs")
    public ResponseEntity<?> getAllBlocs() {
        List<Bloc> blocList = blocService.retrieveBlocs();
        return ResponseEntity.ok(blocList);
    }
    @Operation(
            description = "c'est le service Api qui permet d'ajouter des blocs dans notre base de données ... vous etes les bienvenus",
            summary = "en effet notre fameuse methode permet d'ajouter un bloc a notre base de donnée",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "failed ! ",
                            responseCode = "404"
                    )
            }
    )
    @PostMapping(path = "/add")
    public ResponseEntity<Bloc> AjouterNouveauBloc(@RequestBody Bloc bloc) {
        Bloc BlocNEw = blocService.addBloc(bloc);
        return new ResponseEntity<>(BlocNEw, HttpStatus.CREATED);
    }


    @PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierBloc(@RequestBody Bloc bloc) {
        try {
            Bloc blocUpdating = blocService.updateBloc(bloc);
            return new ResponseEntity<>(blocUpdating, HttpStatus.OK);
        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/recupere_bloc/{id}")
    public ResponseEntity<?> RecupererBloc(@PathVariable("id") long idBloc) {
        Bloc blocExacte = blocService.retrieveBloc(idBloc);
        return ResponseEntity.ok(blocExacte);
    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> SupprimerBloc(@PathVariable("id") long idbloc) {
        try {
            blocService.removeBloc(idbloc);
            return ResponseEntity.ok("Bloc deleted avec succé");
        } catch (RessourceNotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bloc n'existe pas  avec id  " + idbloc);
        }

    }

    @PutMapping(path = "affecterChambreA_Bloc/{nomBloc}")
    public ResponseEntity<?> affecterChambreAbloc( @RequestBody List<Long> numChambres,@PathVariable("nomBloc") String nomBloc) {
        try {
//            Bloc bloc = blocService.affecterChambresABloc(numChambres, nomBloc);
            return new ResponseEntity<>(blocService.affecterChambresABloc(numChambres, nomBloc), HttpStatus.OK);
        } catch (RessourceNotFound Exception) {
            return new ResponseEntity<>(Exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "affecterBlocAfoyer/{idFoyer}/{idBloc}")
    public ResponseEntity<?> affecterBlocForFoyer(@PathVariable("idFoyer") long idFoyer,@PathVariable("idBloc")long idBloc){
    try {
        Bloc blocAffecte=blocService.affecterBlocAFoyer(idBloc,idFoyer);
        return new ResponseEntity<>(blocAffecte,HttpStatus.OK);
    }
    catch (RessourceNotFound exception){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }
}
