package gustavorods.homecart.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "shoppingItem")
@Data
public class ShoppingItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="residence_id")
    private ResidenceModel residence;

    @ManyToOne
    @JoinColumn(name="created_by")
    private UsersModel createdBy;

    private String name;
    private String description;

    private String priority;

    private String status;

    @CreationTimestamp
    private LocalDateTime created_at;
}
