package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.CropDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.CropService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/crop")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class CropController {
    private final CropService cropService;

    @PostMapping
    public ResponseEntity<?> saveCrop(
            @RequestPart("cropName") String cropName,
            @RequestPart("cropType") String cropCategory,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestParam("cropImage") MultipartFile cropImage,
            @RequestParam("FieldCode") String fieldCode
    ) {
        log.info("Request to save crop: name={}, type={}, season={}, scientificName={}, fieldCode={}", cropName, cropCategory, cropSeason, cropScientificName, fieldCode);
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropCommonName(cropName);
        cropDTO.setCategory(cropCategory);
        cropDTO.setCropSeason(cropSeason);
        cropDTO.setCropScientificName(cropScientificName);
        cropDTO.setCropImage(AppUtil.toBase64(cropImage));
        try {
            cropService.saveCrop(cropDTO, fieldCode);
            log.info("Crop saved successfully: {}", cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NotFoundException e) {
            log.error("Field not found for code: {}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistFailedException e) {
            log.error("Failed to save crop: {}", cropDTO, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error while saving crop: {}", cropDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateCrop(
            @RequestPart("cropName") String cropName,
            @RequestPart("cropType") String cropCategory,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestParam("cropImage") MultipartFile cropImage,
            @RequestParam("FieldCode") String fieldCode,
            @PathVariable String id
    ) {
        log.info("Request to update crop: id={}, name={}, type={}, season={}, scientificName={}, fieldCode={}", id, cropName, cropCategory, cropSeason, cropScientificName, fieldCode);
        CropDTO cropDTO = new CropDTO();
        cropDTO.setCropCommonName(cropName);
        cropDTO.setCategory(cropCategory);
        cropDTO.setCropSeason(cropSeason);
        cropDTO.setCropScientificName(cropScientificName);
        cropDTO.setCropImage(AppUtil.toBase64(cropImage));
        try {
            cropService.updateCrop(cropDTO, fieldCode, id);
            log.info("Crop updated successfully: id={}, {}", id, cropDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop or Field not found: id={}, fieldCode={}", id, fieldCode, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistFailedException e) {
            log.error("Failed to update crop: id={}, {}", id, cropDTO, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error while updating crop: id={}, {}", id, cropDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrop(@PathVariable String id) {
        log.info("Request to delete crop: id={}", id);
        try {
            cropService.deleteCrop(id);
            log.info("Crop deleted successfully: id={}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop not found for deletion: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Internal server error while deleting crop: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCrop(@PathVariable String id) {
        log.info("Request to retrieve crop: id={}", id);
        try {
            var crop = cropService.getCrop(id);
            log.info("Crop retrieved successfully: id={}, {}", id, crop);
            return new ResponseEntity<>(crop, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Crop not found: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Internal server error while retrieving crop: id={}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllCrops() {
        log.info("Request to retrieve all crops");
        try {
            var crops = cropService.getAllCrops();
            log.info("All crops retrieved successfully: count={}", crops.size());
            return new ResponseEntity<>(crops, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal server error while retrieving all crops", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}