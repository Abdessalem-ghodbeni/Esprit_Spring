package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.AuthenticationResponse;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.RefreshTokenRequest;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.User;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services.IAuthenticationServices;
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationServices authenticationServices;

    @PostMapping("/registerEtudiant")
    public Etudiant registerEtudiant(@RequestBody Etudiant etudiant) {
        return authenticationServices.registerEtudiant(etudiant);
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
