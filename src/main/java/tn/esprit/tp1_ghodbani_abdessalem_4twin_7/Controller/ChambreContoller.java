package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.IChambreServices;

import java.util.List;

@RestController
@RequestMapping(path="/chambre")
@RequiredArgsConstructor
public class ChambreContoller {
    private final IChambreServices chambreService;

    @GetMapping(path = "/all_chambres")
    public ResponseEntity<?> getAllChambre(){

        try{
            List<Chambre> chambres=chambreService.retrieveAllChambres();
            if (chambres.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Liste est vide ");
            }
            return ResponseEntity.ok(chambres);
        }
        catch (RessourceNotFound exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("quelque chose mal passé");
        }
    }


    @PostMapping(path = "/add")
    public ResponseEntity<Chambre>AjouterNouvelleChambre(@RequestBody Chambre chambre){
        try {
            Chambre nouvelleChambre=chambreService.addChambre(chambre);
            return new ResponseEntity<>(nouvelleChambre,HttpStatus.CREATED);
        }
        catch (RessourceNotFound exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<Chambre> EditChambre(@RequestBody Chambre chambre){
        try{
             Chambre nouvelleChambre=chambreService.updateChambre(chambre);
         return new ResponseEntity<>(nouvelleChambre,HttpStatus.OK);

        }
        catch (RessourceNotFound exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }
    @GetMapping(path = "/recupere/{id}")
    public ResponseEntity<?> getChambreById(@PathVariable("id") long idChambre){
        try{
            Chambre chambre=chambreService.retrieveChambre(idChambre);
            if (chambre==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chambre introuvable avec cet id "+idChambre);
            }
           return ResponseEntity.ok(chambre);
        }
        catch (RessourceNotFound exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("quelque chose mal passé");
        }
    }
//    @PostMapping("/{nomBloc}/affecterChambres")
//    public ResponseEntity<Bloc> affecterChambresABloc(@PathVariable String nomBloc, @RequestBody List<String> numeroChambre) {
//        Bloc bloc = chambreService.affecterChambresABloc(numeroChambre, nomBloc);
//        return ResponseEntity.ok(bloc);
//    }
}
