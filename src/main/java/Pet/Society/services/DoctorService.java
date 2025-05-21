package Pet.Society.services;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.models.exceptions.UserNotFoundException;
import Pet.Society.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class DoctorService  {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorEntity save(DoctorEntity doctor) {
        try {
            return this.doctorRepository.save(doctor);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Doctor");
        }
    }

    public DoctorEntity findById(Long id) {
        Optional<DoctorEntity> doctor = this.doctorRepository.findById(id);
        if (doctor.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
        return doctor.get();
    }

    public void update(DoctorEntity doctorToModify, Long id) {
        Optional<DoctorEntity> existingDoctor = this.doctorRepository.findById(id);
        if (existingDoctor.isEmpty()){
            throw new UserNotFoundException("Doctor does not exist");
        }
        if(doctorToModify.getName() != null) existingDoctor.get().setName(doctorToModify.getName());
        if(doctorToModify.getSurname() != null) existingDoctor.get().setSurname(doctorToModify.getSurname());
        if(doctorToModify.getEmail() != null) existingDoctor.get().setEmail(doctorToModify.getEmail());
        if(doctorToModify.getPhone() != null) existingDoctor.get().setPhone(doctorToModify.getPhone());
        if(doctorToModify.getDni() != null) existingDoctor.get().setDni(doctorToModify.getDni());
        if(doctorToModify.getSpeciality() != null) existingDoctor.get().setSpeciality(doctorToModify.getSpeciality());

        this.doctorRepository.save(existingDoctor.get());
    }

    public void unSubscribe(Long id){
        Optional<DoctorEntity> existingDoctor = this.doctorRepository.findById(id);
        if (existingDoctor.isEmpty()){
            throw new UserNotFoundException("Doctor does not exist");
        }
        DoctorEntity doctorToUnsubscribe = existingDoctor.get();
        doctorToUnsubscribe.setSubscribed(false);
        this.doctorRepository.save(doctorToUnsubscribe);
    }

    public Optional<DoctorEntity> findByDNI(String DNI){
        return this.doctorRepository.findById(Long.valueOf(DNI));
    }

}
