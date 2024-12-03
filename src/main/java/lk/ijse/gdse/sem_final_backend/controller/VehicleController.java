package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.dto.impl.VehicleDTO;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        log.info("Request to save vehicle: {}", vehicleDTO);
        try {
            vehicleService.addVehicle(vehicleDTO);
            log.info("Vehicle saved successfully: {}", vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            log.error("Vehicle already exists: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            log.error("Failed to persist vehicle data: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while saving vehicle: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{vehicleCode}", params = "staffId")
    public ResponseEntity<?> updateVehicle(
            @Valid @RequestBody VehicleDTO vehicleDTO,
            @RequestParam("staffId") String staffId,
            @PathVariable("vehicleCode") String vehicleCode) {
        log.info("Request to update vehicle with code {}: {}", vehicleCode, vehicleDTO);
        try {
            vehicleService.updateVehicle(vehicleDTO, staffId, vehicleCode);
            log.info("Vehicle updated successfully: {}", vehicleDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Vehicle not found: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            log.error("Failed to persist vehicle update: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while updating vehicle: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{vehicleCode}")
    public ResponseEntity<?> getVehicle(@PathVariable String vehicleCode) {
        log.info("Request to fetch vehicle with code: {}", vehicleCode);
        try {
            return new ResponseEntity<>(vehicleService.getVehicle(vehicleCode), HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Vehicle not found for code {}: {}", vehicleCode, e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while fetching vehicle: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{vehicleCode}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleCode) {
        log.info("Request to delete vehicle with code: {}", vehicleCode);
        try {
            vehicleService.deleteVehicle(vehicleCode);
            log.info("Vehicle deleted successfully for code: {}", vehicleCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Vehicle not found for deletion: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while deleting vehicle: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        log.info("Request to fetch all vehicles");
        try {
            return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("No vehicles found: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error while fetching all vehicles: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
