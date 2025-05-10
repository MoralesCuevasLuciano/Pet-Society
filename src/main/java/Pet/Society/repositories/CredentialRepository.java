package Pet.Society.repositories;

import Pet.Society.models.entities.CredentialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<CredentialEntity, Long> {
}
