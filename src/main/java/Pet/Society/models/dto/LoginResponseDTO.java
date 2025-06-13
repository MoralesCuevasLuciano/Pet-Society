package Pet.Society.models.dto;

import Pet.Society.models.enums.Role;

public class LoginResponseDTO {
    private final String token;
    private final Long id;

    public LoginResponseDTO(String token, Long id) {
        this.id = id;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
