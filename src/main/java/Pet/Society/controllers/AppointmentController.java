package Pet.Society.controllers;

import Pet.Society.models.dto.AppointmentDTO;
import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.repositories.AppointmentRepository;
import Pet.Society.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointment) {
        this.appointmentService.save(appointment);
        return ResponseEntity.ok(appointment);
    }
    //prueba, es posible que se necesite un DTO
    @PostMapping("/create2")
    public ResponseEntity<AppointmentEntity> createAppointment2(@RequestBody AppointmentDTO appointment) {
        AppointmentEntity appointmentEntity = this.appointmentService.save2(appointment);

        return ResponseEntity.ok(appointmentEntity);
    }

    @PatchMapping("/assign/{id}")
    public ResponseEntity<AppointmentEntity> assignAppointment(@RequestBody AppointmentEntity appointment, PetEntity pet) {
        this.appointmentService.bookAppointment(appointment,pet);
        return ResponseEntity.ok(appointment);
    }
}
