package Pet.Society.repositories;

import Pet.Society.models.entities.PetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, Long> {
}
