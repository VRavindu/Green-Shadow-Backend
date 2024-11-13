package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.CropDetailsDTO;
import lk.ijse.gdse.sem_final_backend.entity.Crop;
import lk.ijse.gdse.sem_final_backend.entity.CropDetails;
import lk.ijse.gdse.sem_final_backend.entity.Field;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.repository.CropDetailsRepository;
import lk.ijse.gdse.sem_final_backend.repository.CropRepository;
import lk.ijse.gdse.sem_final_backend.repository.FieldRepository;
import lk.ijse.gdse.sem_final_backend.repository.StaffRepository;
import lk.ijse.gdse.sem_final_backend.service.CropDetailsService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CropDetailsServiceIMPL implements CropDetailsService {
    private final CropDetailsRepository cropDetailsRepository;
    private final Mapping mapping;
    private final FieldRepository fieldRepository;
    private final StaffRepository staffRepository;
    private final CropRepository cropRepository;

    @Override
    public void saveCropDetails(CropDetailsDTO cropDetailsDTO) {
        List<Field> filed = new ArrayList<>();
        List<Crop> crops = new ArrayList<>();
        List<Staff> staff = new ArrayList<>();
        for (String fieldCode : cropDetailsDTO.getFieldCodes()) {
            fieldRepository.findById(fieldCode).ifPresent(filed::add);
        }
        for (String cropCode : cropDetailsDTO.getCropCodes()) {
            cropRepository.findByCropCode(cropCode).ifPresent(crops::add);
        }
        for (String staffId : cropDetailsDTO.getStaffIds()) {
            staffRepository.findById(staffId).ifPresent(staff::add);
        }
        String logCode = AppUtil.createCropDetailsID();
        cropDetailsDTO.setLogCode(logCode);
        CropDetails cropDetails = mapping.convertCropDetailsDTOToCropDetails(cropDetailsDTO);
        cropDetails.setField(filed);
        cropDetails.setCrop(crops);
        cropDetails.setStaff(staff);
        CropDetails save = cropDetailsRepository.save(cropDetails);
        if (save == null){
            throw new DataPersistFailedException("Crop details save failed");
        }
    }
    @Override
    public void updateCropDetails(CropDetailsDTO cropDetailsDTO , String logCode) {
        Optional<CropDetails> cropDetailsByLogCode = cropDetailsRepository.findCropDetailsByLogCode(logCode);
        if (cropDetailsByLogCode.isPresent()){
            cropDetailsByLogCode.get().setLogDetails(cropDetailsDTO.getLogDetails());
            cropDetailsByLogCode.get().setObservedImage(cropDetailsDTO.getObservedImage());
        }else {
            throw new NotFoundException("Crop details not found");
        }
    }
}