package lk.ijse.gdse.sem_final_backend.customObj.errorRespose;

import lk.ijse.gdse.sem_final_backend.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleErrorResponse implements Serializable, VehicleResponse {
    private int errorCode;
    private String errorMessage;
}
