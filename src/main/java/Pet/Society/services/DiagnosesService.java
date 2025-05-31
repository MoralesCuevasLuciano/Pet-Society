package Pet.Society.services;

import Pet.Society.models.dto.DiagnosesDTO;
import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.DiagnosesEntity;
import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.PetEntity;
import Pet.Society.models.exceptions.AppointmentNotFoundException;
import Pet.Society.models.exceptions.DiagnosesNotFoundException;
import Pet.Society.models.exceptions.DoctorNotFoundException;
import Pet.Society.models.exceptions.PetNotFoundException;
import Pet.Society.repositories.AppointmentRepository;
import Pet.Society.repositories.DiagnosesRepository;
import Pet.Society.repositories.DoctorRepository;
import Pet.Society.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DiagnosesService {

    private final DiagnosesRepository diagnosesRepository;
    private final PetRepository petRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DiagnosesService(DiagnosesRepository diagnosesRepository,
                            PetRepository petRepository,
                            DoctorRepository doctorRepository,
                            AppointmentRepository appointmentRepository) {
        this.diagnosesRepository = diagnosesRepository;
        this.petRepository = petRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public DiagnosesEntity save(DiagnosesDTO dto) {
        PetEntity pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));

        DoctorEntity doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        AppointmentEntity appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));

        if (dto.getDate().isAfter(java.time.LocalDateTime.now())) {
            throw new IllegalArgumentException("the date entered is incorrect");
        }

        DiagnosesEntity diagnosis = new DiagnosesEntity(
                dto.getDiagnose(),
                dto.getTreatment(),
                doctor,
                pet,
                appointment,
                dto.getDate()
        );

        return diagnosesRepository.save(diagnosis);
    }

    public DiagnosesEntity findById(Long id) {
        return diagnosesRepository.findById(id)
                .orElseThrow(() -> new DiagnosesNotFoundException("Diagnosis " + id + " not found"));
    }

    public List<DiagnosesEntity> findAll() {
        return diagnosesRepository.findAll();
    }

    public DiagnosesEntity findLastById(long id) {
        return diagnosesRepository.findLastById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found"));
    }

    public Page<DiagnosesEntity> findByPetId(long id, Pageable pageable) {
        if (diagnosesRepository.findByPetId(id, pageable) != null) {
            return diagnosesRepository.findByPetId(id, pageable);
        }else {
            throw new DiagnosesNotFoundException("Diagnoses of Pet id : " + id + " not found");
        }

    }


}
