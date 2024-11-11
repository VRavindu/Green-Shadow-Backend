package lk.ijse.gdse.sem_final_backend.controller;

import lk.ijse.gdse.sem_final_backend.dto.impl.FieldDTO;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.service.FieldService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/field")
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
    ){
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setFieldLocation(new Point(fieldLocationX, fieldLocationY));
        fieldDTO.setFieldSize(fieldSize);
        fieldDTO.setImage1(AppUtil.toBase64(image1));
        fieldDTO.setImage2(AppUtil.toBase64(image2));
        try {
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
