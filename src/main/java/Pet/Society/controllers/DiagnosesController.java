package Pet.Society.controllers;


import Pet.Society.models.dto.DiagnosesDTO;
import Pet.Society.models.dto.DiagnosesDTOResponse;
import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.services.DiagnosesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getLastDiagnoses/{id}")
    public ResponseEntity<DiagnosesDTOResponse> getLastDiagnostic(@PathVariable long id) {
        return ResponseEntity.ok(diagnosesService.findLastById(id));
    }

    @GetMapping("getByPetId/{id}")
    public ResponseEntity<Page<DiagnosesDTOResponse>> getByPetId(@PageableDefault(size = 10,page = 0) Pageable pageable, @PathVariable long id) {
        return ResponseEntity.ok(diagnosesService.findByPetId(id, pageable));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<DiagnosesDTOResponse>> getAllDiagnoses(@PageableDefault(size = 10,page = 0) Pageable pageable) {
        return ResponseEntity.ok(diagnosesService.findAll(pageable));
    }

    @GetMapping("/getByDoctorId/{id}")
    public ResponseEntity<Page<DiagnosesDTOResponse>> getByDoctorId(@PathVariable long id, @PageableDefault(size = 10,page = 0) Pageable pageable) {
        return ResponseEntity.ok(diagnosesService.findByDoctorId(id, pageable));
    }

}