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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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

    public AppointmentEntity(long id, LocalDateTime startDate, LocalDateTime endDate, Reason reason, Status status, DoctorEntity doctor, DiagnosesEntity diagnoses, PetEntity pet, boolean approved) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.doctor = doctor;
        this.diagnoses = diagnoses;
        this.pet = pet;
        this.approved = approved;
    }

    public AppointmentEntity(LocalDateTime startDate, LocalDateTime endDate, Reason reason, Status status, DoctorEntity doctor, DiagnosesEntity diagnoses, PetEntity pet, boolean approved) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.doctor = doctor;
        this.diagnoses = diagnoses;
        this.pet = pet;
        this.approved = approved;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public DiagnosesEntity getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(DiagnosesEntity diagnoses) {
        this.diagnoses = diagnoses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
