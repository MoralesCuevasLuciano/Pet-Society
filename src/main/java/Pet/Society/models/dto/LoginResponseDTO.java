package Pet.Society.models.dto;

import Pet.Society.models.enums.Role;

public class LoginResponseDTO {
    private Role role;
    private String message;

    public LoginResponseDTO(Role role, String message) {
        this.role = role;
        this.message = message;
    }

    public Role getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}
