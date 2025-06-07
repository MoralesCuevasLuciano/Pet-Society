package Pet.Society.controllers;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.services.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/create") //WORKS
    public ResponseEntity<DoctorEntity> createDoctor(@Valid @RequestBody DoctorEntity doctor) {
        DoctorEntity doctorEntity = doctorService.save(doctor);
        return new ResponseEntity<>(doctorEntity, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}") //WORKS
    public ResponseEntity<DoctorEntity> updateDoctor(@Valid @RequestBody DoctorEntity doctor, @PathVariable Long id) {
        doctorService.update(doctor, id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);

    }

    @PatchMapping("/unsubscribe/{id}") //WORKS
    public ResponseEntity<DoctorEntity> unsuscribe(@PathVariable Long id) {
        doctorService.unSubscribe(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/find/{id}") //WORKS
    public ResponseEntity<DoctorEntity> findById(@PathVariable Long id) {
        DoctorEntity doctor = doctorService.findById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/find/dni/{dni}") //WORKS
    public ResponseEntity<DoctorEntity> findByDNI (@PathVariable String dni){
        DoctorEntity doctor = doctorService.findByDni(dni);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/addDoctors")
    public ResponseEntity<List<DoctorEntity>> addDoctors() {
        return ResponseEntity.ok(this.doctorService.addDoctors());
    }
}