package lk.ijse.gdse.sem_final_backend.dto.impl;

import jakarta.validation.constraints.*;
import lk.ijse.gdse.sem_final_backend.customObj.UserResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotNull
    @Pattern(regexp = "OTHER|MANAGER|ADMINISTRATIVE|SCIENTIST")
    private String role;
}
