package gustavorods.homecart.dto;

import gustavorods.homecart.model.UsersModel;
import jakarta.validation.constraints.NotBlank;

public record ResidenceRequestDTO(
    @NotBlank(message = "name is required")
    String name,

    @NotBlank(message = "ownerEmail is required")
    String ownerEmail
) {
}
