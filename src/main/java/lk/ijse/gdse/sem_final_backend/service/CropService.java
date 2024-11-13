package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.CropResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.CropDTO;
import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO, String fieldCode);
    void updateCrop(CropDTO cropDTO, String fieldCode, String id);
    void deleteCrop(String id);
    CropResponse getCrop(String id);
    List getAllCrops();
}