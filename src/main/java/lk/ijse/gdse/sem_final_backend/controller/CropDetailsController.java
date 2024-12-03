package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.customObj.CropDetailsResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.CropDetailsDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.CropDetailsService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cropDetails")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class CropDetailsController {
    private final CropDetailsService cropDetailsService;

    @PostMapping
    public ResponseEntity<?> saveCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImg") MultipartFile observedImg,
            @RequestParam(value = "fieldCode") List<String> fieldCodes,
            @RequestParam(value = "cropCode") List<String> cropCodes,
            @RequestParam(value = "staffId") List<String> staffIds
    ) {
        log.info("Saving crop details with logDetails={}, fieldCodes={}, cropCodes={}, staffIds={}",
                logDetails, fieldCodes, cropCodes, staffIds);

        try {
            String observedImageBase64 = AppUtil.toBase64(observedImg);
            CropDetailsDTO cropDetailsDTO = new CropDetailsDTO();
            cropDetailsDTO.setLogDate(new Date());
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(observedImageBase64);
            cropDetailsDTO.setFieldCodes(fieldCodes);
            cropDetailsDTO.setCropCodes(cropCodes);
            cropDetailsDTO.setStaffIds(staffIds);

            cropDetailsService.saveCropDetails(cropDetailsDTO);
            log.info("Crop details saved successfully: {}", cropDetailsDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Failed to save crop details: logDetails={}", logDetails, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error occurred while saving crop details: logDetails={}", logDetails, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/{logCode}")
    public ResponseEntity<?> updateCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImg") MultipartFile observedImg,
            @PathVariable(value = "logCode") String logCode
    ) {
        log.info("Updating crop details with logCode={}, logDetails={}", logCode, logDetails);

        try {
            String observedImageBase64 = AppUtil.toBase64(observedImg);
            CropDetailsDTO cropDetailsDTO = new CropDetailsDTO();
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(observedImageBase64);

            cropDetailsService.updateCropDetails(cropDetailsDTO, logCode);
            log.info("Crop details updated successfully: logCode={}", logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop details not found for logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error occurred while updating crop details: logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{logCode}")
    public ResponseEntity<?> deleteCropDetailsByLogCode(@PathVariable String logCode) {
        log.info("Deleting crop details with logCode={}", logCode);

        try {
            cropDetailsService.deleteCropDetailsByLogCode(logCode);
            log.info("Crop details deleted successfully: logCode={}", logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop details not found for deletion: logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error occurred while deleting crop details: logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{logCode}")
    public ResponseEntity<?> findCropDetailsByLogCode(@PathVariable String logCode) {
        log.info("Retrieving crop details with logCode={}", logCode);

        try {
            CropDetailsResponse cropDetailsResponse = cropDetailsService.findCropDetailsByLogCode(logCode);
            log.info("Crop details retrieved successfully: logCode={}", logCode);
            return new ResponseEntity<>(cropDetailsResponse, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop details not found: logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error occurred while retrieving crop details: logCode={}", logCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllCropDetails() {
        log.info("Retrieving all crop details");

        try {
            var cropDetailsList = cropDetailsService.getAllCropDetails();
            log.info("All crop details retrieved successfully: count={}", cropDetailsList.size());
            return new ResponseEntity<>(cropDetailsList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error occurred while retrieving all crop details", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
