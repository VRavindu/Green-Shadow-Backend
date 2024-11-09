package lk.ijse.gdse.sem_final_backend.dto.impl;

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
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffId;
}
