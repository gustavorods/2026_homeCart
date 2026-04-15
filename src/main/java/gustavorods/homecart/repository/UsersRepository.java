package gustavorods.homecart.repository;

import gustavorods.homecart.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findById(long id);
    Optional<UsersModel> findByEmail(String email);
}
