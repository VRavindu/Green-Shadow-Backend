package lk.ijse.gdse.sem_final_backend.dto.impl;

import lk.ijse.gdse.sem_final_backend.customObj.EquipmentResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private String fieldCode;
    private String staffId;
}
