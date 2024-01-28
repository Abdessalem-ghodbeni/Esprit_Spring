package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Reservation;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.TypeChambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IChambreRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IEtudiantRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IReservationServiceImpl implements IReservationService {
    private final IReservationRepository reservationRepository;
    private final IChambreRepository chambreRepository;
    private final IEtudiantRepository etudiantRepository;

    @Override
    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> retrieveAllReservation() {
        Iterable<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> reservationList = new ArrayList<>();
        reservations.forEach(reservationList::add);
        return reservationList;
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        Optional<Reservation> reservationExisting = reservationRepository.findById(res.getIdReservation());
        if (reservationExisting.isPresent()) {
            Reservation reservationUpdated = reservationExisting.get();
            reservationUpdated.setDebutAnneeUniversitaire(res.getDebutAnneeUniversitaire());
            reservationUpdated.setFinAnneUniversitaire(res.getFinAnneUniversitaire());
            reservationUpdated.setEstActive(res.isEstActive());
            reservationUpdated.setEtudiants(res.getEtudiants());
            reservationUpdated.setNumReservation(res.getNumReservation());
            return  reservationRepository.save(reservationUpdated);

        } else {
            throw new RessourceNotFound("Failed update ! pas de reservation avec cet id " + res.getIdReservation());
        }

    }

    @Override
    public Reservation retrieveReservation(long idReservation) {
        Optional<Reservation> reservationToget = reservationRepository.findById(idReservation);
        if (reservationToget.isPresent()) {
            Reservation reservationGetting = reservationToget.get();
            return reservationGetting;
        } else {
            throw new RessourceNotFound("accune reservation avec l'id :" + idReservation);
        }
    }
//    @Transactional
//
//    @Override
//    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
//        Chambre chambre =chambreRepository.findById(idChambre).orElseThrow(()-> new RessourceNotFound("pas de chambre avec cet id "+idChambre));
//        Etudiant etudiantByCin=etudiantRepository.findByCin(cinEtudiant);
//        Reservation reservation=new Reservation();
//        Reservation newReservation=this.add(reservation);
//        etudiantByCin.setReservations((Set<Reservation>) newReservation);
//        chambre.setReservations((Set<Reservation>) newReservation);
//
//        return newReservation;
//    }

    @Transactional
    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);

        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        // Création de la réservation
        Reservation reservation = new Reservation();
        assert chambre != null;
        reservation.setNumReservation(chambre.getNumeroChambre() +"-"+ chambre.getBloc().getNomBloc().replace(" ", "") +"-"+ cinEtudiant);
        reservation.setDebutAnneeUniversitaire(LocalDate.of(LocalDate.now().getYear(), 9, 1));
        reservation.setFinAnneUniversitaire(LocalDate.of(LocalDate.now().getYear() + 1, 6, 1));
        reservation.setEstActive(true);

        // Déterminer la capacité maximale en fonction du type de chambre
        int capaciteMax = 0;
        if (TypeChambre.SIMPLE.equals(chambre.getTypeC())) {
            capaciteMax = 1;
        } else if (TypeChambre.DOUBLE.equals(chambre.getTypeC())) {
            capaciteMax = 2;
        } else if (TypeChambre.TRIPLE.equals(chambre.getTypeC())) {
            capaciteMax = 3;
        }

        // Vérifier si la capacité maximale de la chambre est atteinte
        long nombreReservations = chambre.getReservations().size();
        if (nombreReservations >= capaciteMax) {
            throw new IllegalStateException("La capacité maximale de la chambre est atteinte.");
        }

        // Gérer la relation ManyToMany
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // Sauvegarder la réservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Ajouter la réservation à la collection de réservations de la chambre et sauvegarder
        chambre.getReservations().add(savedReservation);
        chambreRepository.save(chambre);

        return savedReservation;
    }



    @Override
    public Reservation annulerReservation(Long cinEtudiant) {
        // Trouver l'étudiant et sa réservation
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        // Supposition: chaque étudiant a au maximum une réservation valide
        Reservation reservation = etudiant.getReservations().stream()
                .filter(Reservation::isEstActive)
                .findFirst()
                .orElse(null);

        // Mettre à jour l'état de la réservation
        reservation.setEstActive(false);

        // Désaffecter l'étudiant
        reservation.getEtudiants().remove(etudiant);

        // Désaffecter la chambre associée et mettre à jour sa capacité
        Chambre chambreAssociee = chambreRepository.findByReservationsContains(reservation);
        if (chambreAssociee != null) {
            chambreAssociee.getReservations().remove(reservation);
            chambreRepository.save(chambreAssociee); // Sauvegarder les changements dans la chambre
        }

        // Sauvegarder les modifications
        return reservationRepository.save(reservation);
    }


}
