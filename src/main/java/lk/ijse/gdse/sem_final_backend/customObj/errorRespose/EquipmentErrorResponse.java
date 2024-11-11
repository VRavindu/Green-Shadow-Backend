package lk.ijse.gdse.sem_final_backend.customObj.errorRespose;

import lk.ijse.gdse.sem_final_backend.customObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentErrorResponse implements EquipmentResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
