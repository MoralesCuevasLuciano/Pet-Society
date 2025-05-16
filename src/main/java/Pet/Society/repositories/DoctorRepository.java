package Pet.Society.repositories;

import Pet.Society.models.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

}
