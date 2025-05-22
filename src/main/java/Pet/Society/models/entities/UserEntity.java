package Pet.Society.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2, max = 50, message = "Error en nombre")
    private String name;
    @NotNull
    @Size(min = 2, max = 50, message = "Error en apellido")
    private String surname;
    @NotNull
    @Size(min = 9, max = 20, message = "Error en celular")
    private String phone;
    @NotNull
    @Size(min = 7, max = 8, message = "Error en dni")
    @Column(unique = true)
    private String dni;
    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    @ColumnDefault("1")
    private boolean isSubscribed;



    public UserEntity(long id, String name, String surname, String phone, String dni, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.dni = dni;
        this.email = email;
    }

    public UserEntity(String name, String surname, String phone, String dni, String email) {
        this.name = this.name;
        this.surname = this.surname;
        this.phone = this.phone;
        this.dni = this.dni;
        this.email = this.email;
    }

    public UserEntity() {

    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setDNI(String dni) {
    }
}
