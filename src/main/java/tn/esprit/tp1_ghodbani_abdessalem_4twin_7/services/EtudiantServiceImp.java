package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IEtudiantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImp implements IEtudiantServices {

    private final IEtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        List<Etudiant> etudiantList = etudiants.stream().map(etudiant -> etudiantRepository.save(etudiant)).collect(Collectors.toList());
        return etudiantList;
    }
    // on peux utiliser saveAll c'est une methode prédefinie par spring boot au lieu de parcour liste puis

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findById(e.getIdEtudiant());
        if (existingEtudiant.isPresent()) {
            Etudiant etudiantUpdated = existingEtudiant.get();
            etudiantUpdated.setNomEtudiant(e.getNomEtudiant());
            etudiantUpdated.setPrenom(e.getPrenom());
            etudiantUpdated.setCin(e.getCin());
            etudiantUpdated.setDateNaissance(e.getDateNaissance());
            etudiantUpdated.setEcole(e.getEcole());
            etudiantUpdated.setReservations(e.getReservations());
            return etudiantUpdated;

        } else {
            throw new RessourceNotFound("etudiant not found avec id : " + e.getIdEtudiant());
        }


    }

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        return etudiants;
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {
//       Etudiant EtudiantById=etudiantRepository.findById(idEtudiant).orElseThrow(()-> new RessourceNotFound("etudiant n'exite pas avec id"+idEtudiant));
        Optional<Etudiant> isExistingStudent = etudiantRepository.findById(idEtudiant);
        if (isExistingStudent.isPresent()) {
            Etudiant StudentIsHere = isExistingStudent.get();
            return StudentIsHere;

        } else {
            throw new RessourceNotFound("Etudiant n'existe pas avec cet id : " + idEtudiant);
        }
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        Optional<Etudiant> isHere = etudiantRepository.findById(idEtudiant);
        if (isHere.isPresent()) {
            Etudiant StudentToDeleted = isHere.get();
            etudiantRepository.delete(StudentToDeleted);

        } else {
            throw new RessourceNotFound("etudiant n'existe pas avec id " + idEtudiant);
        }
    }
}



