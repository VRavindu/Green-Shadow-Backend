package lk.ijse.gdse.sem_final_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
