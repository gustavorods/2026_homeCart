package gustavorods.homecart.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "residenceMember")
@Data
public class ResidenceMemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residence_id")
    private ResidenceModel residence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersModel user;

    private String role;

    @CreationTimestamp
    private LocalDateTime created_at;

}
