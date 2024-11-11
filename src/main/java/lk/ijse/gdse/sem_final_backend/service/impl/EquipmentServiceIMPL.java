package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.EquipmentDTO;
import lk.ijse.gdse.sem_final_backend.entity.Equipment;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.repository.EquipmentRepository;
import lk.ijse.gdse.sem_final_backend.service.EquipmentService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceIMPL implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.createEquipmentId());
        Equipment save = equipmentRepository.save(mapping.convertEquipmentDTOToEquipment(equipmentDTO));
        if (save == null) {
            throw new DataPersistFailedException("Equipment not saved");
        }
    }
}
