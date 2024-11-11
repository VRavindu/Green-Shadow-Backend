package lk.ijse.gdse.sem_final_backend.util;

import lk.ijse.gdse.sem_final_backend.dto.impl.StaffDTO;
import lk.ijse.gdse.sem_final_backend.dto.impl.UserDTO;
import lk.ijse.gdse.sem_final_backend.dto.impl.VehicleDTO;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import lk.ijse.gdse.sem_final_backend.entity.User;
import lk.ijse.gdse.sem_final_backend.entity.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper mapper;
    //USER
    public User convertUserDTOToUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }
    public UserDTO convertUserToUserDTO(User user){
        return mapper.map(user, UserDTO.class);
    }
    //STAFF
    public Staff convertStaffDTOToStaff(StaffDTO staffDTO){
        return mapper.map(staffDTO, Staff.class);
    }
    public StaffDTO convertStaffToStaffDTO(Staff staff){
        return mapper.map(staff, StaffDTO.class);
    }
    public List convertStaffListToStaffDTOList(List<Staff> all) {
        return mapper.map(all, List.class);
    }
    //VEHICLE
    public Vehicle convertVehicleDTOToVehicle(VehicleDTO vehicleDTO){
        return mapper.map(vehicleDTO, Vehicle.class);
    }
    public VehicleDTO convertVehicleToVehicleDTO(Vehicle vehicle){
        return mapper.map(vehicle, VehicleDTO.class);
    }
    public List convertVehicleListToVehicleDTOList(List<Vehicle> all) {
        return mapper.map(all, List.class);
    }
}
