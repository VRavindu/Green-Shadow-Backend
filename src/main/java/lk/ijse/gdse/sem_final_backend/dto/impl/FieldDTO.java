package lk.ijse.gdse.sem_final_backend.dto.impl;

import lk.ijse.gdse.sem_final_backend.customObj.FieldResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double fieldSize;
    private String image1;
    private String image2;
    private List<String> staffId;
}
