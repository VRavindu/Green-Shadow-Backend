package lk.ijse.gdse.sem_final_backend.dto.impl;

import lk.ijse.gdse.sem_final_backend.customObj.StaffRespose;
import lk.ijse.gdse.sem_final_backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffRespose {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    private String email;
    private String gender;
    private String joinedDate;
    private String dob;
    private String contactNo;
    private String role;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;

}
