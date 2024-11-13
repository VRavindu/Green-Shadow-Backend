package lk.ijse.gdse.sem_final_backend.controller;

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
        try {
            String updateBase64ProfilePic = AppUtil.toBase64(observedImg);
            CropDetailsDTO cropDetailsDTO = new CropDetailsDTO();
            cropDetailsDTO.setLogDate(new Date());
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(updateBase64ProfilePic);
            cropDetailsDTO.setFieldCodes(fieldCodes);
            cropDetailsDTO.setCropCodes(cropCodes);
            cropDetailsDTO.setStaffIds(staffIds);
            cropDetailsService.saveCropDetails(cropDetailsDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/{logCode}")
    public ResponseEntity<?> updateCropDetails(
            @RequestPart(value = "logDetails") String logDetails,
            @RequestPart(value = "observedImg") MultipartFile observedImg,
            @PathVariable(value = "logCode") String logCode
    ) {
        try {
            String updateBase64ProfilePic = AppUtil.toBase64(observedImg);
            CropDetailsDTO cropDetailsDTO = new CropDetailsDTO();
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setObservedImage(updateBase64ProfilePic);
            cropDetailsService.updateCropDetails(cropDetailsDTO, logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
