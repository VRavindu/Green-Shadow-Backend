package lk.ijse.gdse.sem_final_backend.repository;

import lk.ijse.gdse.sem_final_backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByLicensePlateNumber(String licensePlateNumber);
}
