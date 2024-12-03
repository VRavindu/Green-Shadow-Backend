package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.customObj.StaffResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
@CrossOrigin("*")
@Slf4j
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<?> saveStaff(@Valid @RequestBody StaffDTO staffDTO) {
        log.info("Request to save staff: {}", staffDTO);
        try {
            staffService.saveStaff(staffDTO);
            log.info("Staff saved successfully: {}", staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Failed to save staff: {}", staffDTO, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateStaff(@Valid @RequestBody StaffDTO staffDTO, @PathVariable("id") String id) {
        log.info("Request to update staff: id={}, staffDTO={}", id, staffDTO);
        try {
            staffService.updateStaff(staffDTO, id);
            log.info("Staff updated successfully: id={}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException | DataPersistFailedException e) {
            log.error("Failed to update staff: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error during staff update: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaff(@PathVariable String id) {
        log.info("Request to get staff details: id={}", id);
        try {
            StaffResponse staffResponse = staffService.getStaffById(id);
            log.info("Staff details retrieved successfully: {}", staffResponse);
            return new ResponseEntity<>(staffResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Staff not found: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error while retrieving staff: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StaffResponse> deleteStaff(@PathVariable String id) {
        log.info("Request to delete staff: id={}", id);
        try {
            staffService.deleteStaff(id);
            log.info("Staff deleted successfully: id={}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Failed to delete staff: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error during staff deletion: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllStaff() {
        log.info("Request to get all staff");
        try {
            var allStaff = staffService.getAllStaff();
            log.info("All staff retrieved successfully: count={}", allStaff.size());
            return new ResponseEntity<>(allStaff, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal server error while retrieving all staff", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
