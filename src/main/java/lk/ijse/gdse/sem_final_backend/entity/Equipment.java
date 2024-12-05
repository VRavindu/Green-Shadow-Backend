package lk.ijse.gdse.sem_final_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @Column(name = "equipment_id")
    private String equipmentId;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "equipment_type")
    private String equipmentType;

    @Column(name = "availability_status")
    private String status;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)  // Cascade delete and update for Field relationship
    @JoinColumn(name = "field_code", referencedColumnName = "field_code")
    private Field field;

    @OneToOne(optional = true, cascade = CascadeType.ALL)  // Cascade delete and update for Staff relationship
    @JoinColumn(name = "staff_member_id", referencedColumnName = "staff_member_id")
    private Staff staff;
}
