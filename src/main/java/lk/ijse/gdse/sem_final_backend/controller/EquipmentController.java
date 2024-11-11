package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.EquipmentDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<?> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
