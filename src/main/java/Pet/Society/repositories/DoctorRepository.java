package Pet.Society.repositories;

import Pet.Society.models.entities.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {

}
