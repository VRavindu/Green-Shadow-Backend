package lk.ijse.gdse.sem_final_backend.service;

import lk.ijse.gdse.sem_final_backend.customObj.VehicleResponse;
import lk.ijse.gdse.sem_final_backend.dto.impl.VehicleDTO;

public interface VehicleService {
    void addVehicle(VehicleDTO vehicle);
    void updateVehicle(VehicleDTO vehicleDTO, String staffId , String vehicleCode);
    VehicleResponse getVehicle(String vehicleCode);
    void deleteVehicle(String vehicleCode);
}
