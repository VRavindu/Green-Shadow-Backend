package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.EquipmentDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping(value = {"/{equipmentId}"}, params = {"staffIds", "fieldCode"})
    public ResponseEntity<?> updateEquipment(
            @PathVariable("equipmentId") String equipmentId,
            @RequestBody EquipmentDTO equipmentDTO,
            @RequestParam("staffIds") String staffId,
            @RequestParam("fieldCode") String fieldCode) {
        try {
            equipmentService.updateEquipment(equipmentDTO, staffId, fieldCode , equipmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Data persistence issue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Not found issue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(@PathVariable String equipmentId) {
        try {
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
