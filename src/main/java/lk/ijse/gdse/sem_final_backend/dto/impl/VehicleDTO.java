package lk.ijse.gdse.sem_final_backend.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.gdse.sem_final_backend.customObj.VehicleResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9-]+$")
    private String licensePlateNumber;
    @NotBlank
    private String vehicleCategory;
    @NotBlank
    private String fuelType;
    @NotBlank
    private String status;
    @Size(max = 200)
    private String remarks;
    private String staffId;
}
