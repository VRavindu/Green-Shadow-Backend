package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.dto.impl.VehicleDTO;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.addVehicle(vehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
