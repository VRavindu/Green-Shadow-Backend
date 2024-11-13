package lk.ijse.gdse.sem_final_backend.customObj.errorRespose;

import lk.ijse.gdse.sem_final_backend.customObj.CropDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsErrorResponse implements Serializable, CropDetailsResponse {
    private int errorCode;
    private String errorMessage;
}