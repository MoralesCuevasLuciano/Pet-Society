package Pet.Society.services;

import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.models.exceptions.DiagnosesNotFoundException;
import Pet.Society.models.exceptions.PetNotFoundException;
import Pet.Society.repositories.DiagnosesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

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

//    public Page<DiagnosesEntity> findByPetId(long id, Pageable pageable) {
//        if (diagnosesRepository.findById(id).isPresent()) {
//            return diagnosesRepository.findByPetId(id, pageable);
//        }else {
//            throw new DiagnosesNotFoundException("Diagnoses of Pet id : " + id + " not found");
//        }
//
//    }


}
