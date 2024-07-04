package ma.micda.journal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.micda.journal.models.User;

@Repository
public interface UserRepositoryNaf extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
