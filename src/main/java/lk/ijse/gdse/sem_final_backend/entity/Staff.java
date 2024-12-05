package lk.ijse.gdse.sem_final_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_member_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "date_of_birth")
    private Date DOB;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "address_line_4")
    private String addressLine4;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    // Many-to-many relationship with Field (no cascade unless required)
    @ManyToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Field> field;

    // Many-to-many relationship with CropDetails (no cascade unless required)
    @ManyToMany(mappedBy = "staff")
    @JsonIgnore
    private List<CropDetails> cropDetails;

    // One-to-one relationship with Equipment (Cascade persist or delete if required)
    @OneToOne(mappedBy = "staff", optional = true)
    @JsonIgnore
    private Equipment equipment;

    // One-to-many relationship with Vehicle (cascade delete if needed)
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vehicle> vehicles;
}
