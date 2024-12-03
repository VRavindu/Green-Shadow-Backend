package lk.ijse.gdse.sem_final_backend.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.gdse.sem_final_backend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    boolean existsByEmail(@NotBlank @Email String email);
}
