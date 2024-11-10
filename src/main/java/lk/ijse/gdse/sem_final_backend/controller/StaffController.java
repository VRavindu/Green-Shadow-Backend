package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {
    private final StaffService staffService;
    @PostMapping
    public ResponseEntity<?> saveStaff(@Valid @RequestBody StaffDTO staffDTO){
        try {
            System.out.println("StaffDTO: " + staffDTO);
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
