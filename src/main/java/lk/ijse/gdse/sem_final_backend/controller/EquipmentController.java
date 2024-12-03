package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.EquipmentDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/equipment")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<?> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        log.info("Request to save equipment: {}", equipmentDTO);
        try {
            equipmentService.saveEquipment(equipmentDTO);
            log.info("Equipment saved successfully: {}", equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persistence failed while saving equipment: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while saving equipment: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = {"/{equipmentId}"}, params = {"staffIds", "fieldCode"})
    public ResponseEntity<?> updateEquipment(
            @PathVariable("equipmentId") String equipmentId,
            @RequestBody EquipmentDTO equipmentDTO,
            @RequestParam("staffIds") String staffId,
            @RequestParam("fieldCode") String fieldCode) {
        log.info("Request to update equipment: {}, StaffId: {}, FieldCode: {}", equipmentId, staffId, fieldCode);
        try {
            equipmentService.updateEquipment(equipmentDTO, staffId, fieldCode, equipmentId);
            log.info("Equipment updated successfully: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            log.error("Data persistence issue during update: {}", e.getMessage(), e);
            return new ResponseEntity<>("Data persistence issue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            log.error("Equipment not found during update: {}", e.getMessage(), e);
            return new ResponseEntity<>("Not found issue: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during update: {}", e.getMessage(), e);
            return new ResponseEntity<>("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{equipmentId}")
    public ResponseEntity<?> deleteEquipment(@PathVariable String equipmentId) {
        log.info("Request to delete equipment: {}", equipmentId);
        try {
            equipmentService.deleteEquipment(equipmentId);
            log.info("Equipment deleted successfully: {}", equipmentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Equipment not found for deletion: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during deletion: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{equipmentId}")
    public ResponseEntity<?> getEquipmentById(@PathVariable String equipmentId) {
        log.info("Request to get equipment by ID: {}", equipmentId);
        try {
            return new ResponseEntity<>(equipmentService.getEquipment(equipmentId), HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            log.error("Data persistence failed while fetching equipment by ID: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while fetching equipment by ID: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllEquipments() {
        log.info("Request to fetch all equipments");
        try {
            return new ResponseEntity<>(equipmentService.getAllEquipment(), HttpStatus.OK);
        } catch (DataPersistFailedException e) {
            log.error("Data persistence failed while fetching all equipments: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while fetching all equipments: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
