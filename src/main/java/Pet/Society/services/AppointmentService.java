package Pet.Society.services;

import Pet.Society.models.dto.AppointmentDTO;
import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.enums.Reason;
import Pet.Society.models.enums.Status;
import Pet.Society.models.exceptions.DuplicatedAppointmentException;
import Pet.Society.repositories.AppointmentRepository;
import Pet.Society.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PetService petService;

    //MAYBE IS THE CORRECT WAY.
    //Si ya existe la cita; Excepcion
    //Si existe una cita que se solape con otra; Excepción
    public void save(AppointmentEntity appointment) {

        if (isOverlapping(appointment)) {
            throw new DuplicatedAppointmentException("The appointment already exists; it has the same hour.");
        }

        this.appointmentRepository.save(appointment);
    }
        /// FALTA POR TOCAR, ES POSIBLE QUE SE NECESITE UN DTO
    public AppointmentEntity save2 (AppointmentDTO appointmentDTO) {
        DoctorEntity findDoctor = this.doctorService.findById(appointmentDTO.getDoctor().getId());

        AppointmentEntity appointment = new AppointmentEntity(appointmentDTO.getStartTime()
                , appointmentDTO.getEndTime(),
                appointmentDTO.getReason(),
                Status.SUCCESFULLY,
                findDoctor);
        if (isOverlapping(appointment)) {
            throw new DuplicatedAppointmentException("The appointment already exists; it has the same hour.");
        }
        return this.appointmentRepository.save(appointment);
    }

    /// FORMA 1
    public void bookAppointment(AppointmentEntity appointment, PetEntity pet) {

    }
    /// FORMA 2
    public void bookAppointment2(Long idAppointment, Long petId) {

    }
    //FORMA 3
    //USANDO UN DTO, PERO DEBO MODIFICAR EL EXISTENTE.
    public void bookAppointment3(AppointmentDTO appointmentDTO ) {

    }

    //Confirm if an Appointment doesn't overlap with another Appointment
    /// return if exist any match with another appointment in our database.
    private boolean isOverlapping(AppointmentEntity newAppointment) {
        return appointmentRepository.findAppointmentByDoctor(newAppointment.getDoctor())
                .stream()
                .anyMatch(existing ->
                        newAppointment.getStartDate().isBefore(existing.getEndDate()) &&
                                newAppointment.getEndDate().isAfter(existing.getStartDate())
                );
    }

}
