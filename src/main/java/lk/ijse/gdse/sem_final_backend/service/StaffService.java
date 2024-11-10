package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(StaffDTO staffDTO, String id);
}
