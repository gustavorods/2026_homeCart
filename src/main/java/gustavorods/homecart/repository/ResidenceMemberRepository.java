package gustavorods.homecart.repository;

import gustavorods.homecart.model.ResidenceMemberModel;
import gustavorods.homecart.model.ResidenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidenceMemberRepository extends JpaRepository<ResidenceMemberModel, Long> { }
