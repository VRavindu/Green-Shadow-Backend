package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.FieldDTO;
import lk.ijse.gdse.sem_final_backend.entity.Field;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.repository.FieldRepository;
import lk.ijse.gdse.sem_final_backend.repository.StaffRepository;
import lk.ijse.gdse.sem_final_backend.service.FieldService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceIMPL implements FieldService {
    private final FieldRepository fieldRepository;
    private final Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.createFieldCode());
        Field save = fieldRepository.save(mapping.convertFieldDTOToField(fieldDTO));
        if (save == null){
            throw new DataPersistFailedException("Field save failed");
        }
    }
}
