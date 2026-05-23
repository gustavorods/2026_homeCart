package gustavorods.homecart.repository;

import gustavorods.homecart.model.ResidenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidenceRepository extends JpaRepository<ResidenceModel, Long> {
    boolean existsByInviteCode(int invite_code);

    Optional<ResidenceModel> findByInviteCode(int invite_code);
}
