package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.repository.StaffRepository;
import lk.ijse.gdse.sem_final_backend.service.StaffService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceIMPL implements StaffService {
    private final StaffRepository staffRepository;
    private final Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        String staffID = AppUtil.createStaffID();
        while (staffRepository.existsById(staffID)) {
            staffID = AppUtil.createStaffID();
        }
        staffDTO.setId(staffID);
        Staff save = staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
        if (save == null){
            throw new DataPersistFailedException("Staff save failed");
        }
    }
}