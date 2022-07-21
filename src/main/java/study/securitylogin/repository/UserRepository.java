package study.securitylogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.securitylogin.domain.AppUser;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
}
