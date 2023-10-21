package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

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

@RequiredArgsConstructor
@RequestMapping(path = "/bloc")

public class BlocController {

    private final BlocServiceImp blocService;


    @GetMapping(path = "/all/blocs")
    public ResponseEntity<?> getAllBlocs() {
        List<Bloc> blocList = blocService.retrieveBlocs();
return ResponseEntity.ok(blocList);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Bloc> AjouterNouveauBloc(@RequestBody Bloc bloc){
        Bloc BlocNEw=blocService.addBloc(bloc);
        return new ResponseEntity<>(BlocNEw, HttpStatus.CREATED);
    }


@PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierBloc(@RequestBody Bloc bloc){
        try {
            Bloc blocUpdating = blocService.updateBloc(bloc);
            if(blocUpdating==null){
                return new ResponseEntity<>("No bloc with this id ",HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(blocUpdating,HttpStatus.OK);
        }
        catch (RessourceNotFound exception){
            return new ResponseEntity<>("accun bloc dans la liste",HttpStatus.NOT_FOUND);
        }
}

@GetMapping(path = "/recupere_bloc/{id}")
public ResponseEntity<?> RecupererBloc(@PathVariable("id")long idBloc){
            Bloc blocExacte=blocService.retrieveBloc(idBloc);
            return ResponseEntity.ok(blocExacte);
}



@DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String> SupprimerBloc(@PathVariable("id") long idbloc){
        try{
            blocService.removeBloc(idbloc);
            return ResponseEntity.ok("Bloc deleted avec succé");
        }
        catch (RessourceNotFound exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bloc n'existe pas  avec id  "+idbloc);
        }

}






}
