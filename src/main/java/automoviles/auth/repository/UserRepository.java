package automoviles.auth.repository;

import automoviles.auth.entity.User;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.username = ?1, u.email = ?2 WHERE u.id = ?3")
    void updateUser(String username, String email, Long id);

}
