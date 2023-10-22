package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;

import java.util.List;

public interface IUniversityService {

    List<Universite> retrieveAllUniversities();
    Universite addUniversity (Universite u);
    Universite updateUniversity (Universite u);
    Universite retrieveUniversity (long idUniversity);
    void removeUniversity(long idUniversity);
}
