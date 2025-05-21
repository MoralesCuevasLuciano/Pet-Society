package Pet.Society.controllers;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.services.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<DoctorEntity> createDoctor(@Valid @RequestBody DoctorEntity doctor){
        try {
            DoctorEntity doctorEntity = doctorService.save(doctor);
            return new ResponseEntity<>(doctorEntity, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<DoctorEntity> updateDoctor(@Valid @RequestBody DoctorEntity doctor, @PathVariable Long id){
        try {
            doctorService.update(doctor, id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/unsubscribe/{id}")
    public ResponseEntity<DoctorEntity> unsuscribe (@PathVariable Long id){
        try {
            doctorService.unSubscribe(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DoctorEntity> findById(@PathVariable Long id){
        try {
            DoctorEntity doctor = doctorService.findById(id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/dni/{dni}")
    public ResponseEntity<DoctorEntity> findByDNI(@PathVariable String dni){
        try {
            DoctorEntity doctor = doctorService.findByDNI(dni).get();
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
