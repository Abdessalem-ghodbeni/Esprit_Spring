package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Foyer;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.FoyerServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/logement")
public class FoyerController
{
    private final FoyerServiceImpl foyerService;
    @PostMapping(path = "/add_foyer")
    public ResponseEntity<Foyer> AddFoyer (@RequestBody Foyer NouveauFoyer){
        Foyer newFoyer=foyerService.ajouterFoyer(NouveauFoyer);
        return new ResponseEntity<>(newFoyer, HttpStatus.CREATED) ;

}
@GetMapping(path = "/get/foyer/{id}")
    public ResponseEntity<Foyer> getFoyerById(@PathVariable("id") long FoyerId){

       Foyer foyerDemande=foyerService.getFoyer(FoyerId);
       return ResponseEntity.ok(foyerDemande);
}

@GetMapping(path = "/get/all_foyes")
    public ResponseEntity<List<Foyer>> getAllFoyer(){
        List<Foyer> foyers=foyerService.getAllFoyer();
        return ResponseEntity.ok(foyers);
}
@DeleteMapping(path = "/supprimer/foyer/{id}")
    public ResponseEntity<String>SupprimerFoyer(@PathVariable("id") long foyerId){
foyerService.supprimerFoyer(foyerId);
return ResponseEntity.ok("Foyer deleted Successfuly");
}

}
