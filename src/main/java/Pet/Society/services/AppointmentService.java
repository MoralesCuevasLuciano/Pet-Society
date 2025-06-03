package Pet.Society.services;

import Pet.Society.models.dto.AppointmentDTO;
import Pet.Society.models.dto.AppointmentUpdateDTO;
import Pet.Society.models.dto.AssingmentPetDTO;
import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.enums.Reason;
import Pet.Society.models.enums.Status;
import Pet.Society.models.exceptions.AppointmentDoesntExistException;
import Pet.Society.models.exceptions.DuplicatedAppointmentException;
import Pet.Society.models.exceptions.UnavailableAppointmentException;
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
    @Autowired
    private ClientService clientService;

    //MAYBE IS THE CORRECT WAY.
    //Si ya existe la cita; Excepcion
    //Si existe una cita que se solape con otra; Excepción

        /// FALTA POR TOCAR, ES POSIBLE QUE SE NECESITE UN DTO
    public AppointmentEntity save (AppointmentDTO appointmentDTO) {
        DoctorEntity findDoctor = this.doctorService.findById(appointmentDTO.getDoctor().getId());

        AppointmentEntity appointment = new AppointmentEntity(appointmentDTO.getStartTime()
                , appointmentDTO.getEndTime(),
                appointmentDTO.getReason(),
                Status.TO_BEGIN,
                findDoctor,true);
        if (isOverlapping(appointment)) {
            throw new DuplicatedAppointmentException("The appointment already exists; it has the same hour.");
        }
        return this.appointmentRepository.save(appointment);
    }


    /// FORMA 2
    public AppointmentEntity bookAppointment(Long idAppointment, AssingmentPetDTO dto) {
        AppointmentEntity findAppointment = this.appointmentRepository.
                findById(idAppointment).orElseThrow(() -> new AppointmentDoesntExistException("Appointment not found"));

        if (findAppointment.getPet() != null) {
            throw new UnavailableAppointmentException("This appointment is already booked");
        }
        if(findAppointment.isApproved()) {
            throw new UnavailableAppointmentException("The client has an unpaid appointment");
        }

        PetEntity findPet = this.petService.getPetById(dto.getPetId());

        findAppointment.setPet(findPet);

        return this.appointmentRepository.save(findAppointment);
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

    ///WORKS
    public AppointmentEntity updateAppointment(AppointmentUpdateDTO appointmentUpdateDTO, long id) {
        Optional<AppointmentEntity> existingAppointment = this.appointmentRepository.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new AppointmentDoesntExistException("Appointment does not exist");
        }
        AppointmentEntity appointmentToUpdate = existingAppointment.get();
        if (appointmentUpdateDTO.getApproved() != null) {
            appointmentToUpdate.setApproved(appointmentUpdateDTO.getApproved());
        }

        if (appointmentUpdateDTO.getReason() != null) {
            appointmentToUpdate.setReason(appointmentUpdateDTO.getReason());
        }

        if (appointmentUpdateDTO.getStatus() != null) {
            appointmentToUpdate.setStatus(appointmentUpdateDTO.getStatus());
        }

        if (appointmentUpdateDTO.getPetId() != null) {
            PetEntity pet = this.petService.getPetById(appointmentUpdateDTO.getPetId());
            appointmentToUpdate.setPet(pet);
        }

        if (appointmentUpdateDTO.getDiagnosesId() != null) {
            // Aquí deberías buscar el Diagnóstico por ID y asignarlo a la cita
            // DiagnosesEntity diagnoses = this.diagnosesService.getDiagnosesById(appointmentUpdateDTO.getDiagnosesId());
            // appointmentToUpdate.setDiagnoses(diagnoses);
        }
        this.appointmentRepository.save(appointmentToUpdate);
        return appointmentToUpdate;
    }

    public void cancelAppointment(long id) {
        Optional<AppointmentEntity> existingAppointment = this.appointmentRepository.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new AppointmentDoesntExistException("Appointment does not exist");
        }
        AppointmentUpdateDTO appointmentUpdateDTO = new AppointmentUpdateDTO();
        appointmentUpdateDTO.setStatus(Status.CANCELED);
        updateAppointment(appointmentUpdateDTO, id);
    }

    public List<AppointmentEntity> getAllAppointmentsByClientId(long id) {
        Optional<ClientEntity> client = Optional.ofNullable(this.clientService.findById(id));
        if (client.isEmpty()) {
            throw new AppointmentDoesntExistException("Client does not exist");
        }
        return this.appointmentRepository.findAllByPetClientId(id);
    }

    public List<AppointmentEntity> getAllAppointmentsByPetId(long id) {
        if(!petService.existsPetById(id)){
            throw new AppointmentDoesntExistException("Pet does not exist");
        }
        return this.appointmentRepository.findAllByPetId(id).stream().limit(10).toList();
    }

    public List<AppointmentEntity> getAllAppointmentsByDoctorId(long id) {
        if(!doctorService.doctorExistById(id)){
            throw new AppointmentDoesntExistException("Doctor does not exist");
        }
        return this.appointmentRepository.findAllByDoctorId(id).stream().limit(10).toList();
    }

    public boolean petHasAppointment(long id) {
        List<AppointmentEntity> appointments = this.appointmentRepository.findAllByPetId(id);
        if(!appointments.isEmpty()){
           return true;
        }
       return false;
    }
}
