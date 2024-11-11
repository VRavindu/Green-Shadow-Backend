package lk.ijse.gdse.sem_final_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.sem_final_backend.customObj.StaffResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffController {
    private final StaffService staffService;
    @PostMapping
    public ResponseEntity<?> saveStaff(@Valid @RequestBody StaffDTO staffDTO){
        try {
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateStaff(@Valid @RequestBody StaffDTO staffDTO , @PathVariable("id") String id){
        try {
            staffService.updateStaff(staffDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException | DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaff(@PathVariable String id){
        try {
            StaffResponse staffResponse = staffService.getStaffById(id);
            return new ResponseEntity<>(staffResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StaffResponse> deleteStaff(@PathVariable String id){
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
