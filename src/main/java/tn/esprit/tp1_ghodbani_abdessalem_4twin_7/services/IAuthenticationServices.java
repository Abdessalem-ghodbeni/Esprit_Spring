package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.AuthenticationResponse;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.RefreshTokenRequest;

public interface IAuthenticationServices {
    Etudiant registerEtudiant(Etudiant etudiant);
    AuthenticationResponse login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}
