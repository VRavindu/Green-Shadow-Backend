package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.FieldResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.FieldDTO;
import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void updateField(FieldDTO fieldDTO , List<String> staffIds);
    void deleteField(String fieldCode);
    FieldResponse getField(String fieldCode);
    List getAllFields();
}
