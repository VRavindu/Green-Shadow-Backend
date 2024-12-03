package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.FieldDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.exception.NotFoundException;
import lk.ijse.gdse.sem_final_backend.service.FieldService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/field")
@CrossOrigin("*")
@Slf4j
public class FieldController {
    private final FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocationX") int fieldLocationX,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("fieldLocationY") int fieldLocationY
    ) {
        log.info("Request to save field: fieldName={}, fieldLocationX={}, fieldLocationY={}, fieldSize={}",
                fieldName, fieldLocationX, fieldLocationY, fieldSize);
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setFieldLocation(new Point(fieldLocationX, fieldLocationY));
        fieldDTO.setFieldSize(fieldSize);
        fieldDTO.setImage1(AppUtil.toBase64(image1));
        fieldDTO.setImage2(AppUtil.toBase64(image2));
        try {
            fieldService.saveField(fieldDTO);
            log.info("Field saved successfully: {}", fieldDTO);
            return new ResponseEntity<>(fieldDTO, HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed for field: {}", fieldDTO, e);
            return new ResponseEntity<>("Data persist failed", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error while saving field", e);
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{fieldCode}", params = "staffIds")
    public ResponseEntity<?> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocationX") int fieldLocationX,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("fieldLocationY") int fieldLocationY,
            @RequestParam("staffIds") List<String> staffIds
    ) {
        log.info("Request to update field: fieldCode={}, fieldName={}, staffIds={}",
                fieldCode, fieldName, staffIds);
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldCode(fieldCode);
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setFieldLocation(new Point(fieldLocationX, fieldLocationY));
        fieldDTO.setFieldSize(fieldSize);
        fieldDTO.setImage1(AppUtil.toBase64(image1));
        fieldDTO.setImage2(AppUtil.toBase64(image2));
        try {
            fieldService.updateField(fieldDTO, staffIds);
            log.info("Field updated successfully: {}", fieldDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Field not found for update: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            log.error("Data persist failed during field update: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error during field update", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<?> deleteField(@PathVariable String fieldCode) {
        log.info("Request to delete field: fieldCode={}", fieldCode);
        try {
            fieldService.deleteField(fieldCode);
            log.info("Field deleted successfully: fieldCode={}", fieldCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Field not found for deletion: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error during field deletion: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{fieldCode}")
    public ResponseEntity<?> getField(@PathVariable String fieldCode) {
        log.info("Request to get field details: fieldCode={}", fieldCode);
        try {
            var field = fieldService.getField(fieldCode);
            log.info("Field details retrieved successfully: {}", field);
            return new ResponseEntity<>(field, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Field not found: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Internal server error while retrieving field details: fieldCode={}", fieldCode, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllFields() {
        log.info("Request to get all fields");
        try {
            var fields = fieldService.getAllFields();
            log.info("All fields retrieved successfully: count={}", fields.size());
            return new ResponseEntity<>(fields, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal server error while retrieving all fields", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}