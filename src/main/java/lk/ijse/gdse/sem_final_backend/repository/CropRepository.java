package lk.ijse.gdse.sem_final_backend.repository;

import lk.ijse.gdse.sem_final_backend.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
}