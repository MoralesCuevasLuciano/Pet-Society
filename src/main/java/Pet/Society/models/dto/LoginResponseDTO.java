package Pet.Society.models.dto;

import Pet.Society.models.enums.Role;

public class LoginResponseDTO {
        public Role role;
        public String message;

        public LoginResponseDTO(Role role, String message) {
            this.role = role;
            this.message = message;
        }
}
