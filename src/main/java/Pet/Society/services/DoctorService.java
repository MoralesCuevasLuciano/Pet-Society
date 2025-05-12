package Pet.Society.services;

import Pet.Society.models.entities.DoctorEntity;
import Pet.Society.repositories.DoctorRepository;

import java.util.Optional;

public class DoctorService implements DoctorRepository {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public <S extends DoctorEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends DoctorEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DoctorEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<DoctorEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<DoctorEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(DoctorEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DoctorEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
