package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.EquipmentResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.EquipmentDTO;
import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(EquipmentDTO equipmentDTO ,String staffId , String fieldCode , String equipmentId);
    void deleteEquipment(String equipmentId);
    EquipmentResponse getEquipment(String equipmentId);
    List<EquipmentResponse> getAllEquipment();
}
