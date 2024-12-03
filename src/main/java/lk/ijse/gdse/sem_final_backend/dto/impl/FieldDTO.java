package lk.ijse.gdse.sem_final_backend.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lk.ijse.gdse.sem_final_backend.customObj.FieldResponse;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ]+$")
    private String fieldName;
    @NotBlank
    private Point fieldLocation;
    @Positive
    @NotNull // Changed to @NotNull to avoid conflicts
    private double fieldSize;
    @NotNull
    private String image1;
    @NotNull
    private String image2;
    @NotNull
    private List<String> staffId;
}
