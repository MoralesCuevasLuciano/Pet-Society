package Pet.Society.services;

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
    public AppointmentEntity save2 (LocalDateTime start, LocalDateTime end, Reason reason, DoctorEntity doctor) {
        DoctorEntity findDoctor = this.doctorService.findById(doctor.getId());
        AppointmentEntity appointment = new AppointmentEntity(start,end,reason,Status.SUCCESFULLY,findDoctor);
        return this.appointmentRepository.save(appointment);
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
