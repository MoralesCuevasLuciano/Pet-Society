package Pet.Society.services;


import Pet.Society.models.entities.ClientEntity;
import Pet.Society.models.exceptions.UserNotFoundException;
import Pet.Society.repositories.ClientRepository;
import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity save(ClientEntity client) {
            try{
                return this.clientRepository.save(client);
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Client");
            }
    }

    /*public ClientEntity findById(int id) {

    }*/ // Metodo incompleto, descomentar cuando esté hecho

    //SOLO RECIBE UN JSON COMPLETO.
    public void update(ClientEntity clientToModify, Long id) {
        Optional<ClientEntity> existingClient = this.clientRepository.findById(id);
        if (existingClient.isEmpty()){
            throw new UserNotFoundException("User does not exist");
        }
        this.clientRepository.save(clientToModify);
    }

    public void unSubscribe(Long id){
        Optional<ClientEntity> existingClient = this.clientRepository.findById(id);
        if (existingClient.isEmpty()){
            throw new UserNotFoundException("User does not exist");
        }
        ClientEntity clientToUnsubscribe = existingClient.get();
        clientToUnsubscribe.setSubscribed(false);
        this.clientRepository.save(clientToUnsubscribe);
    }

    public Optional<ClientEntity> findByDNI(String DNI){
        return this.clientRepository.findByDni(DNI);
    }

}

