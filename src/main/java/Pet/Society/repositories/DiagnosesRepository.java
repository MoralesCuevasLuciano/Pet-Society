package Pet.Society.repositories;

import Pet.Society.models.entities.DiagnosesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosesRepository extends JpaRepository<DiagnosesEntity, Long> {

    @Query(value = "SELECT d FROM DiagnosesEntity d WHERE d.pet.id = :id ORDER BY d.date DESC LIMIT 1")
    Optional<DiagnosesEntity> findLastById (@Param("id") Long id);
}
