package Pet.Society.repositories;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity> findByDni (String Dni);
}
