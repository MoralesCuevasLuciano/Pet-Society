package Pet.Society.controllers;

import Pet.Society.models.entities.AppointmentEntity;
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
    public ResponseEntity<AppointmentEntity> createAppointment2(@RequestBody AppointmentEntity appointment) {
        return ResponseEntity.ok(this.appointmentService.save2(appointment.getStartDate(),
                appointment.getEndDate(),
                appointment.getReason(),
                appointment.getDoctor()));
    }
}
