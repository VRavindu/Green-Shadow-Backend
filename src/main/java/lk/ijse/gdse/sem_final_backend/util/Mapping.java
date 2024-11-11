package lk.ijse.gdse.sem_final_backend.util;

import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper mapper;

    public User convertUserDTOToUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
    public UserDTO convertUserToUserDTO(User user){
        return mapper.map(user, UserDTO.class);
    }

    public Staff convertStaffDTOToStaff(StaffDTO staffDTO){
        return mapper.map(staffDTO, Staff.class);
    }
    public StaffDTO convertStaffToStaffDTO(Staff staff){
        return mapper.map(staff, StaffDTO.class);
    }
    public List convertStaffListToStaffDTOList(List<Staff> all) {
        return mapper.map(all, List.class);
    }
}
