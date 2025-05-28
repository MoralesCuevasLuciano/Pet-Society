package Pet.Society.repositories;

import Pet.Society.models.entities.AppointmentEntity;
import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findAppointmentByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
    List<AppointmentEntity> findAppointmentByDoctor(DoctorEntity doctor);
    AppointmentEntity findByPetAndId(PetEntity pet, long id);

    AppointmentEntity findByIdAndPetId(long id, long petId);
}
