package lk.ijse.gdse.sem_final_backend.service.impl;

import lk.ijse.gdse.sem_final_backend.dto.impl.VehicleDTO;
import lk.ijse.gdse.sem_final_backend.entity.Vehicle;
import lk.ijse.gdse.sem_final_backend.exception.AlreadyExistsException;
import lk.ijse.gdse.sem_final_backend.exception.DataPersistFailedException;
import lk.ijse.gdse.sem_final_backend.repository.VehicleRepository;
import lk.ijse.gdse.sem_final_backend.service.VehicleService;
import lk.ijse.gdse.sem_final_backend.util.AppUtil;
import lk.ijse.gdse.sem_final_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceIMPL implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final Mapping mapping;
    @Override
    public void addVehicle(VehicleDTO vehicle) {
        String vehicleCode = AppUtil.createVehicleCode();
        vehicle.setVehicleCode(vehicleCode);
        if (vehicleRepository.existsByLicensePlateNumber(vehicle.getLicensePlateNumber())){
            throw new AlreadyExistsException("Vehicle Plate Number Already Exists");
        }else {
            Vehicle save = vehicleRepository.save(mapping.convertVehicleDTOToVehicle(vehicle));
            if (save == null){
                throw new DataPersistFailedException("Vehicle Save failed");
            }
        }
    }
}
