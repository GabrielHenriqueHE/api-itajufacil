package io.github.gabrielhenriquehe.itajufacil.repositories;

import io.github.gabrielhenriquehe.itajufacil.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByPhone(String phone);

}
