package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Etudiant;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Reservation;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IReservationServiceImpl implements IReservationService {
    private final IReservationRepository reservationRepository;

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




}
