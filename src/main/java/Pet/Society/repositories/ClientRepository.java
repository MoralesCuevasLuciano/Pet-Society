package Pet.Society.repositories;

import Pet.Society.models.entities.ClientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

/**
 * Interfaz que define los métodos para interactuar con la base de datos
 * relacionados a la entidad ClientEntity.
 */

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    // Buscar cliente por email
    Optional<ClientEntity> findByEmail(String email);

    // Buscar clientes por apellido
    List<ClientEntity> findBySurname(String surname);

    // Consulta personalizada para buscar clientes que son fundaciones
    @Query("SELECT c FROM ClientEntity c WHERE c.isFoundation = true")
    List<ClientEntity> findAllFoundations();
}







