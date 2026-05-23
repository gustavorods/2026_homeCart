package gustavorods.homecart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddMemberRequestDTO(
        @NotBlank(message = "email is required")
        @Email
        String email,

        @NotBlank(message = "role is required")
        String role,

        @NotBlank(message = "Residence Code is necessary")
        int residenceCode
) {
}
