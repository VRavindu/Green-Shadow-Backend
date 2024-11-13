package lk.ijse.gdse.sem_final_backend.repository;

import lk.ijse.gdse.sem_final_backend.entity.CropDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDetailsRepository extends JpaRepository<CropDetails, String> {
}