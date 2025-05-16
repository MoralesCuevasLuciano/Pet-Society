package Pet.Society.services;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DoctorService  {

    @Autowired
    private DoctorRepository doctorRepository;

}
