package lk.ijse.gdse.sem_final_backend.dto.impl;

import lk.ijse.gdse.sem_final_backend.customObj.CropDetailsResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsDTO implements SuperDTO, CropDetailsResponse {
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private List<String> cropCodes;
    private List<String> fieldCodes;
    private List<String> staffIds;
}
