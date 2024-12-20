package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.customObj.StaffResponse;
import lk.ijse.gdse.sem_final_backend.customObj.errorRespose.StaffErrorResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.repository.StaffRepository;
import lk.ijse.gdse.sem_final_backend.service.StaffService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceIMPL implements StaffService {
    private final StaffRepository staffRepository;
    private final Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        if (!staffRepository.existsByEmail(staffDTO.getEmail())) {
            String staffID = AppUtil.createStaffID();
            staffDTO.setId(staffID);
            Staff save = staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
            if (save == null) {
                throw new DataPersistFailedException("Staff save failed");
            }
        }else {
            throw new AlreadyExistsException("Email already exist");
        }
    }
    @Override
    public void updateStaff(StaffDTO staffDTO, String id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()){
            staffDTO.setId(id);
            Staff save = staffRepository.save(mapping.convertStaffDTOToStaff(staffDTO));
            if (save == null){
                throw new DataPersistFailedException("Staff update failed");
            }
        }else {
            throw new NotFoundException("Staff not found");
        }
    }
    @Override
    public StaffResponse getStaffById(String id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isPresent()){
            return mapping.convertStaffToStaffDTO(staff.get());
        }else {
            return new StaffErrorResponse(404, "Staff not found");
        }
    }
    @Override
    public void deleteStaff(String id) {
        Optional<Staff> existsStaff = staffRepository.findById(id);
        if (existsStaff.isPresent()){
            staffRepository.deleteById(id);
        }else {
            throw new NotFoundException("Staff not found");
        }
    }
    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.convertStaffListToStaffDTOList(staffRepository.findAll());
    }
}
