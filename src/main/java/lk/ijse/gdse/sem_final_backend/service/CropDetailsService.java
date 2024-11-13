package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.CropDetailsResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.CropDetailsDTO;
import java.util.List;

public interface CropDetailsService {
    void saveCropDetails(CropDetailsDTO cropDetailsDTO);
    void updateCropDetails(CropDetailsDTO cropDetailsDTO , String logCode);
    void deleteCropDetailsByLogCode(String logCode);
    CropDetailsResponse findCropDetailsByLogCode(String logCode);
    List<CropDetailsDTO> getAllCropDetails();
}