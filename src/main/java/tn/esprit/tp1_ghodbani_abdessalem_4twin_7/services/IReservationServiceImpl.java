package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Reservation;
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
            return reservationUpdated;
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
        Chambre chambre = chambreRepository.findById(idChambre).orElseThrow(() -> new RessourceNotFound("Pas de chambre avec cet ID " + idChambre));
        Etudiant etudiantByCin = etudiantRepository.findByCin(cinEtudiant);

        if (etudiantByCin == null) {
            throw new RessourceNotFound("Aucun étudiant trouvé avec ce CIN " + cinEtudiant);
        }

        Reservation reservation = new Reservation();
        reservation.setDebutAnneeUniversitaire(LocalDate.now()); // ou la date appropriée
        reservation.setFinAnneUniversitaire(LocalDate.now().plusMonths(9)); // ou la date appropriée
        reservation.setEstActive(true);

        Reservation newReservation = this.add(reservation);

        Set<Reservation> etudiantReservations = etudiantByCin.getReservations();
        if (etudiantReservations == null) {
            etudiantReservations = new HashSet<>();
        }
        etudiantReservations.add(newReservation);
        etudiantByCin.setReservations(etudiantReservations);

        Set<Reservation> chambreReservations = chambre.getReservations();
        if (chambreReservations == null) {
            chambreReservations = new HashSet<>();
        }
        chambreReservations.add(newReservation);
        chambre.setReservations(chambreReservations);

        return newReservation;
    }



}
