package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.dto.impl.CropDTO;

public interface CropService {
    void saveCrop(CropDTO cropDTO, String fieldCode);
    void updateCrop(CropDTO cropDTO, String fieldCode, String id);
}