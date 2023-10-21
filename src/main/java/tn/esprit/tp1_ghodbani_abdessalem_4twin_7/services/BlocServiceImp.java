package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.Exception.RessourceNotFound;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Bloc;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository.IBlocRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlocServiceImp implements IBlocService {
    private final IBlocRepository blocRepository;

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
}
