package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation add(Reservation reservation);

    List<Reservation> retrieveAllReservation();

    Reservation updateReservation(Reservation res);

    Reservation retrieveReservation(long idReservation);
    public Reservation ajouterReservation (long idChambre, long cinEtudiant) ;


    Reservation annulerReservation (Long cinEtudiant) ;

}
