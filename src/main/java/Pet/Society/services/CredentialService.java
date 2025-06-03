package Pet.Society.services;

import Pet.Society.models.entities.CredentialEntity;
import Pet.Society.repositories.CredentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;


    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public List<CredentialEntity> listAll (){
        return credentialRepository.findAll();
    }

    public void delete(Long id){
       credentialRepository.deleteById(id);
    }

    public CredentialEntity save(CredentialEntity c){
        return credentialRepository.save(c);
    }

    public Optional<CredentialEntity> findById(Long id){
        return credentialRepository.findById(id);
    }


    public Optional<CredentialEntity> findByUsernameAndPassword(String username, String password) {
        return credentialRepository.findByUsernameAndPassword(username, password);
    }

}
