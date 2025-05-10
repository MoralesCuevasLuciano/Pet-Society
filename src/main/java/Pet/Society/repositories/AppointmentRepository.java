package Pet.Society.repositories;

import Pet.Society.models.entities.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Long> {

}
