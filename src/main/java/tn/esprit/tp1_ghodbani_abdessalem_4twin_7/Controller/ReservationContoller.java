package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Reservation;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.IReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reservations")
public class ReservationContoller {
    public final IReservationService reservationService;

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getAllReservation() {
        try {
            List<Reservation> reservations = reservationService.retrieveAllReservation();
            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("liste est vide,accun reservation enregistré");
            }
            return ResponseEntity.ok(reservations);

        } catch (RessourceNotFound exceptions) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quelque chose mal passé");
        }


    }

    @PutMapping(path = "/edit")
    public ResponseEntity<?> ModifierReservation(@RequestBody Reservation reservation) {
        try {
            Reservation reservationUpdated = reservationService.updateReservation(reservation);

            return ResponseEntity.ok(reservationUpdated);

        } catch (RessourceNotFound exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping(path = "/get/reservation/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable("id") long idReservation) {
        try {
            Reservation reservationGetted = reservationService.retrieveReservation(idReservation);
            if (reservationGetted == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation non trouvable ");
            }
            return ResponseEntity.ok(reservationGetted);
        } catch (RessourceNotFound Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("quelque chose mal passé");
        }


    }
    @PostMapping(path = "/ajouterReservation")
    public ResponseEntity<?> ajouter_reservation(@RequestBody Reservation reservation){
        try
        {
            Reservation newReservation=reservationService.add(reservation);
            return new ResponseEntity<>(newReservation,HttpStatus.CREATED);
        }
        catch (RessourceNotFound exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
