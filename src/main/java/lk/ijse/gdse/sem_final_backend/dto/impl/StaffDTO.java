package lk.ijse.gdse.sem_final_backend.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse.sem_final_backend.customObj.StaffRespose;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffRespose {
    private String id;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$")
    private String lastName;
    @NotBlank
    private String designation;
    @NotBlank
    private String gender;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String contactNo;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String joinedDate;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String DOB;
    @NotBlank
    private String addressLine1;
    @NotBlank
    private String addressLine2;
    @NotBlank
    private String addressLine3;
    private String addressLine4;
    @NotBlank
    private String role;
}
