package hello.myBoard.repository;

import hello.myBoard.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findOneByRoleName(String roleName);
}
