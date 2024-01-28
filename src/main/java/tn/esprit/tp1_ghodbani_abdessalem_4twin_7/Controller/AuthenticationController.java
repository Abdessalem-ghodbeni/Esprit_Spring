package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.IAuthenticationServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final IAuthenticationServices authenticationServices;

    @PostMapping("/registerEtudiant")
    public ResponseEntity<?> registerEtudiant(@RequestBody Etudiant etudiant) {
        try {

            return new ResponseEntity<>(authenticationServices.registerEtudiant(etudiant), HttpStatus.CREATED);
        } catch (RessourceNotFound ressourceNotFound) {
            return new ResponseEntity<>(ressourceNotFound.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            return new ResponseEntity<>(authenticationServices.registerAdmin(admin), HttpStatus.CREATED);

        } catch (RessourceNotFound ressourceNotFound) {
            return new ResponseEntity<>(ressourceNotFound.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User user) {
        return authenticationServices.login(user.getEmail(), user.getPassword());
    }

    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        return authenticationServices.refreshToken(refreshToken);
    }
}
