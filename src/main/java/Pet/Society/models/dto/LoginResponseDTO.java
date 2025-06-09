package Pet.Society.models.dto;

import Pet.Society.models.enums.Role;

public class LoginResponseDTO {
    private final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
