package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Universite;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IChambreRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class IChambreServices implements IChambreService{
    private final IChambreRepository chambreRepository;


    @Override
    public List<Chambre> retrieveAllChambres() {
        Iterable<Chambre>chambres=chambreRepository.findAll();
        List<Chambre>chambreList=new ArrayList<>();
        chambres.forEach(chambreList::add);
        return chambreList;
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return null;
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return null;
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return null;
    }
}
