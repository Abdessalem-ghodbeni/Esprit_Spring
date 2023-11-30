package tn.esprit.tp1_ghodbani_abdessalem_4twin_7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.Role;
import tn.esprit.tp1_ghodbani_abdessalem_4twin_7.enities.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
