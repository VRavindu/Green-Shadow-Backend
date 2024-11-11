package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.FieldDTO;
import lk.ijse.gdse.sem_final_backend.entity.Field;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.repository.FieldRepository;
import lk.ijse.gdse.sem_final_backend.repository.StaffRepository;
import lk.ijse.gdse.sem_final_backend.service.FieldService;
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
public class FieldServiceIMPL implements FieldService {
    private final FieldRepository fieldRepository;
    private final Mapping mapping;
    private final StaffRepository staffRepository;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.createFieldCode());
        Field save = fieldRepository.save(mapping.convertFieldDTOToField(fieldDTO));
        if (save == null){
            throw new DataPersistFailedException("Field save failed");
        }
    }
    @Override
    public void updateField(FieldDTO fieldDTO , List<String> staffIds) {
        Optional<Field> field = fieldRepository.findById(fieldDTO.getFieldCode());
        if (field.isPresent()) {
            Field TempField1 = mapping.convertFieldDTOToField(fieldDTO);
            List<Staff> staff = new ArrayList<>();
            for (String staffId : staffIds) {
                Optional<Staff> optional = staffRepository.findById(staffId);
                optional.ifPresent(staff::add);
            }
            TempField1.setStaff(staff);
            Field save = fieldRepository.save(TempField1);
            if (save == null) {
                throw new DataPersistFailedException("Field update failed");
            }
        }else {
            throw new NotFoundException("Field not found");
        }
    }
}
