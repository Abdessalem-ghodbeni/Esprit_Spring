package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Chambre;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IBlocRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IChambreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlocServiceImp implements IBlocService {
    private final IBlocRepository blocRepository;
    private final IChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveBlocs() {
        List<Bloc> blocs = blocRepository.findAll();
        return blocs;
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        Optional<Bloc> isHere = blocRepository.findById(bloc.getIdBloc());
        if (isHere.isPresent()) {
            Bloc blocIsExisting = isHere.get();
            blocIsExisting.setNomBloc(bloc.getNomBloc());
            blocIsExisting.setCapaciteBloc(bloc.getCapaciteBloc());
            return blocIsExisting;
        } else {
            throw new RessourceNotFound("pas de bloc avec cet id : " + bloc.getIdBloc());
        }
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        Bloc newBloc = blocRepository.save(bloc);
        return newBloc;
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        Bloc blocIsComing = blocRepository.findById(idBloc).orElseThrow(() -> new RessourceNotFound("Accun bloc de cet id: " + idBloc));
        return blocIsComing;
    }

    @Override
    public void removeBloc(long idBloc) {
        Optional<Bloc> isExisting = blocRepository.findById(idBloc);
        if (isExisting.isPresent()) {
            Bloc blocToDeleted = isExisting.get();
            blocRepository.delete(blocToDeleted);

        } else {
            throw new RessourceNotFound("accun bloc avec cet id " + idBloc);
        }
    }
@Transactional
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) {
        Bloc bloc=blocRepository.findByNomBloc(nomBloc);
        if (!numChambre.isEmpty()){
            for(Long id:numChambre){
                Chambre chambre=chambreRepository.findById(id).orElseThrow(()->new RessourceNotFound("accun bloc avec cet id :"+id));
                chambre.setBloc(bloc);

            }
            return bloc;
        }
        else throw new RessourceNotFound("la liste des numero chambres est vide");
    }


}
