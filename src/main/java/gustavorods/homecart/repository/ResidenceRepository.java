package gustavorods.homecart.repository;

import gustavorods.homecart.model.ResidenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<ResidenceModel, Long> {
    boolean existsByInviteCode(int invite_code);
}
