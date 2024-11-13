package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.CropDTO;
import lk.ijse.gdse.sem_final_backend.entity.Crop;
import lk.ijse.gdse.sem_final_backend.entity.Field;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.repository.CropRepository;
import lk.ijse.gdse.sem_final_backend.repository.FieldRepository;
import lk.ijse.gdse.sem_final_backend.service.CropService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CropServiceIMPL implements CropService {
    private final CropRepository cropRepository;
    private final Mapping mapping;
    private final FieldRepository fieldRepository;
    @Override
    public void saveCrop(CropDTO cropDTO, String fieldCode) {
        cropDTO.setCropCode(AppUtil.createCropCode());
        Crop crop = mapping.convertCropDTOToCrop(cropDTO);
        Field field = fieldRepository.findById(fieldCode).orElseThrow(
                () -> new NotFoundException("Field not found")
        );
        crop.setField(field);
        Crop save = cropRepository.save(crop);
        if (save == null){
            throw new DataPersistFailedException("Crop save failed");
        }
    }
}
