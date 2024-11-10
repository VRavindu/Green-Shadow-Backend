package lk.ijse.gdse.sem_final_backend.customObj.errorRespose;

import lk.ijse.gdse.sem_final_backend.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserErrorResponse implements Serializable, UserResponse {
    private int errorCode;
    private String errorMessage;
}
