package Pet.Society.services;

import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.models.exceptions.PetNotFoundException;
import Pet.Society.repositories.DiagnosesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosesService {

    private final DiagnosesRepository diagnosesRepository;

    @Autowired
    public DiagnosesService(DiagnosesRepository diagnosesRepository) {
        this.diagnosesRepository = diagnosesRepository;
    }

    public DiagnosesEntity findLastById(long id) {
        return diagnosesRepository.findLastById(id).orElseThrow(() -> new PetNotFoundException("Pet not found"));
    }


}
