package Pet.Society.models.entities;

import Pet.Society.models.enums.Reason;
import Pet.Society.models.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private Reason reason;
    private Status status;
    @ManyToOne
    private DoctorEntity doctor;
    @OneToOne
    private DiagnosesEntity diagnoses;
    @ManyToOne
    private PetEntity pet;
    private boolean approved;

    public AppointmentEntity() {
    }

    public AppointmentEntity(LocalDateTime date, Reason reason, Status status, DoctorEntity doctor, PetEntity pet, boolean approved) {
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.doctor = doctor;
        this.pet = pet;
        this.approved = approved;
    }

    public AppointmentEntity(long id, LocalDateTime date, Reason reason, Status status, DoctorEntity doctor, PetEntity pet, boolean approved) {
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.doctor = doctor;
        this.pet = pet;
        this.approved = approved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


}
