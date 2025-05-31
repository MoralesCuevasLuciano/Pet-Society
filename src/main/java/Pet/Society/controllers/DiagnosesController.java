package Pet.Society.controllers;


import Pet.Society.models.dto.DiagnosesDTO;
import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.services.DiagnosesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosesController {

    private final DiagnosesService diagnosesService;

    @Autowired
    public DiagnosesController(DiagnosesService diagnosesService) {
        this.diagnosesService = diagnosesService;
    }

    @PostMapping("/create")
    public ResponseEntity<DiagnosesEntity> createDiagnosis(@RequestBody DiagnosesDTO dto) {
        DiagnosesEntity savedDiagnosis = diagnosesService.save(dto);
        return new ResponseEntity<>(savedDiagnosis, HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<DiagnosesEntity> getDiagnosisById(@PathVariable Long id) {
        DiagnosesEntity diagnosis = diagnosesService.findById(id);
        return ResponseEntity.ok(diagnosis);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DiagnosesEntity>> getAllDiagnoses() {
        List<DiagnosesEntity> diagnoses = diagnosesService.findAll();
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping("/getLastDiagnoses/{id}")
    public ResponseEntity<DiagnosesEntity> getLastDiagnostic(@PathVariable long id) {
        System.out.println("getLastDiagnoses");
        return ResponseEntity.ok(diagnosesService.findLastById(id));
    }

    @GetMapping("getByPetId/{id}")
    public ResponseEntity<Page<DiagnosesDTO>> getByPetId(@PathVariable long id) {
        Pageable pageable = PageRequest.of(0, 10);
        return ResponseEntity.ok(diagnosesService.findByPetId(id, pageable));
    }

}
