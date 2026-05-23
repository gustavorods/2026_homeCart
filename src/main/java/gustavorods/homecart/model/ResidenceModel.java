package gustavorods.homecart.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "residence")
@Data
public class ResidenceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private UsersModel owner;

    @Column(name = "invite_code")
    private int inviteCode;

    @CreationTimestamp
    private LocalDateTime created_at;
}
