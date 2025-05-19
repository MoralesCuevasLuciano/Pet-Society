package Pet.Society.models.controller;

import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.enums.Reason;
import Pet.Society.models.enums.Status;
import Pet.Society.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*** Appointments*/
    /**
     * /**
     * Ver citas disponibles y agendar cita
     */
    @GetMapping("/{clientId}/appointments/available")
    public ResponseEntity<List<String>> getAvailableAppointments(@PathVariable Long clientId) {
        // Generar horarios disponibles de lunes a viernes, de 9:00 a 17:00
        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withHour(9).withMinute(0).withSecond(0).withNano(0);

        // Generar horarios para los próximos 5 días hábiles
        for (int i = 0; i < 5; i++) {
            if (start.getDayOfWeek().getValue() <= 5) { // Lunes a viernes
                for (int hour = 9; hour < 17; hour++) {
                    availableSlots.add(start.withHour(hour));
                }
            }
            start = start.plusDays(1).withHour(9);
        }

        // Convertir horarios a formato legible
        List<String> formattedSlots = availableSlots.stream()
                .map(slot -> slot.toString()) // Puedes formatear según tu preferencia
                .collect(Collectors.toList());

        return ResponseEntity.ok(formattedSlots);
    }

    @PostMapping("/{clientId}/appointments/schedule")
    public ResponseEntity<String> scheduleAppointment(@PathVariable Long clientId, @RequestBody LocalDateTime selectedSlot) {
        // Validar que el horario seleccionado esté disponible
        List<String> availableSlots = getAvailableAppointments(clientId).getBody();
        if (!availableSlots.contains(selectedSlot)) {
            return ResponseEntity.badRequest().body("El horario seleccionado no está disponible.");
        }

        // Asignar un doctor aleatorio
        List<String> doctors = List.of("Dr. Pérez", "Dra. Gómez", "Dr. López");
        String assignedDoctor = doctors.get(new Random().nextInt(doctors.size()));

        // Guardar la cita
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setDate(selectedSlot);
        appointment.setReason(Reason.valueOf("Consulta general"));
        appointment.setStatus(Status.valueOf("Agendada"));
        appointment.setDoctor(assignedDoctor);
        clientService.save(appointment);

        // Retornar mensaje de confirmación
        String confirmationMessage = String.format("Cita agendada para el %s con el %s.", selectedSlot, assignedDoctor);
        return ResponseEntity.ok(confirmationMessage);
    }


    /**
     * Eliminar cita
     */
    @DeleteMapping("/{clientId}/appointments/{appointmentId}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long clientId, @PathVariable Long appointmentId) {
        return clientService.findById(clientId)
                .map(client -> client.getAppointments().stream()
                        .filter(app -> app.getId() == appointmentId)
                        .findFirst()
                        .map(appointment -> {
                            // Verificar si faltan menos de 8 horas para la cita
                            if (appointment.getDate().minusHours(8).isBefore(LocalDateTime.now())) {
                                throw new Pet.Society.exceptions.IllegalStateException("No se puede eliminar la cita. Por favor, llame al local.");
                            }
                            client.getAppointments().remove(appointment);
                            return ResponseEntity.noContent().build();
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * Actualizar cita
     */
    @PutMapping("/{clientId}/appointments/{appointmentId}")
    public ResponseEntity<ClientEntity> updateAppointment(@PathVariable Long clientId, @PathVariable Long appointmentId, @RequestBody AppointmentEntity appointment) {
        return clientService.findById(clientId)
                .map(client -> client.getAppointments().stream()
                        .filter(app -> app.getId() == appointmentId)
                        .findFirst()
                        .map(existingAppointment -> {
                            // Verificar si faltan menos de 8 horas para la cita
                            if (existingAppointment.getDate().minusHours(8).isBefore(LocalDateTime.now())) {
                                throw new Pet.Society.exceptions.IllegalStateException("No se puede actualizar la cita. Por favor, llame al local.");
                            }
                            existingAppointment.setDate(appointment.getDate());
                            existingAppointment.setReason(appointment.getReason());
                            existingAppointment.setStatus(appointment.getStatus());
                            return ResponseEntity.ok(clientService.save(existingAppointment));
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Listar citas
     */
    @GetMapping("/{clientId}/appointments")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentsByClientId(@PathVariable Long clientId) {
        return clientService.findById(clientId)
                .map(client -> ResponseEntity.ok(client.getAppointments()))
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * CRUD para Pet
     */
    @GetMapping("/{clientId}/pets")
    public ResponseEntity<List<PetEntity>> getPetsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getPetsByClientId(clientId));
    }

    @PostMapping("/{clientId}/pets")
    public ResponseEntity<PetEntity> addPet(@PathVariable Long clientId, @RequestBody PetEntity pet) {
        return ResponseEntity.ok(clientService.addPetToClient(clientId, pet));
    }

    @GetMapping("/{clientId}/pets/{petId}")
    public ResponseEntity<PetEntity> getPetById(@PathVariable Long clientId, @PathVariable Long petId) {
        return clientService.getPetsByClientId(clientId).stream()
                .filter(pet -> pet.getId() == petId)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{clientId}/pets/{petId}")
    public ResponseEntity<PetEntity> updatePet(@PathVariable Long clientId, @PathVariable Long petId, @RequestBody PetEntity pet) {
        return clientService.getPetsByClientId(clientId).stream()
                .filter(existingPet -> existingPet.getId() == petId)
                .findFirst()
                .map(existingPet -> {
                    existingPet.setName(pet.getName());
                    existingPet.setAge(pet.getAge());
                    existingPet.setActive(pet.isActive());
                    return ResponseEntity.ok(clientService.addPetToClient(clientId, existingPet));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clientId}/pets/{petId}")
    public ResponseEntity<Object> deletePet(@PathVariable Long clientId, @PathVariable Long petId) {
        return clientService.getPetsByClientId(clientId).stream()
                .filter(pet -> pet.getId() == petId)
                .findFirst()
                .map(pet -> {
                    clientService.getPetsByClientId(clientId).remove(pet);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}