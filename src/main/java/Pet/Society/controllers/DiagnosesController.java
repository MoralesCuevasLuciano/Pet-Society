package Pet.Society.controllers;


import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.services.DiagnosesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diagnoses")
public class DiagnosesController {

    private final DiagnosesService diagnosesService;

    @Autowired
    public DiagnosesController(DiagnosesService diagnosesService) {
        this.diagnosesService = diagnosesService;
    }

    @GetMapping("/getLastDiagnoses/{id}")
    public ResponseEntity<DiagnosesEntity> getLastDiagnostic(@PathVariable long id) {
        System.out.println("getLastDiagnoses");
        return ResponseEntity.ok(diagnosesService.findLastById(id));
    }



}
