package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.dto.impl.CropDetailsDTO;

public interface CropDetailsService {
    void saveCropDetails(CropDetailsDTO cropDetailsDTO);
    void updateCropDetails(CropDetailsDTO cropDetailsDTO , String logCode);
}