package Pet.Society.models.dto;

public class RegisterDTO {
    private String username;
    private String password;
    private boolean isFoundation;
    private String name;
    private String surname;
    private String phone;
    private String DNI;
    private String email;

    public RegisterDTO() {
    }

    public RegisterDTO(String username, String password, boolean isFoundation, String name, String surname, String phone, String DNI, String email) {
        this.username = username;
        this.password = password;
        this.isFoundation = isFoundation;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.DNI = DNI;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isFoundation() {
        return isFoundation;
    }

    public RegisterDTO setFoundation(boolean foundation) {
        isFoundation = foundation;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegisterDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public RegisterDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegisterDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDNI() {
        return DNI;
    }

    public RegisterDTO setDNI(String DNI) {
        this.DNI = DNI;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
