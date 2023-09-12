package hello.myBoard.repository;

import hello.myBoard.domain.UserAccountAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountAuthorityRepository extends JpaRepository<UserAccountAuthority, Long> {
}
