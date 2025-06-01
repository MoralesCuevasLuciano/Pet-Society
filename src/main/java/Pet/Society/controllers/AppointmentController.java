package Pet.Society.controllers;

import Pet.Society.models.dto.AppointmentDTO;
import Pet.Society.models.dto.AppointmentUpdateDTO;
import Pet.Society.models.dto.AssingmentPetDTO;
import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.repositories.AppointmentRepository;
import Pet.Society.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    //prueba, es posible que se necesite un DTO
    @PostMapping("/create")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentDTO appointment) {
        AppointmentEntity appointmentEntity = this.appointmentService.save(appointment);

        return ResponseEntity.ok(appointmentEntity);
    }

    @PatchMapping("/assign/{id}")
    public ResponseEntity<AppointmentEntity> assignAppointment(@PathVariable("id") Long appointmentId, @RequestBody AssingmentPetDTO pet) {
        return ResponseEntity.ok(this.appointmentService.bookAppointment(appointmentId,pet));
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<AppointmentEntity> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateDTO appointmentUpdateDTO) {
        AppointmentEntity appointment = this.appointmentService.updateAppointment(appointmentUpdateDTO, id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentsByClientId(@PathVariable Long clientId) {
            return ResponseEntity.ok(this.appointmentService.getAllAppointmentsByClientId(clientId));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentsByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(this.appointmentService.getAllAppointmentsByPetId(petId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(this.appointmentService.getAllAppointmentsByDoctorId(doctorId));
    }
}
