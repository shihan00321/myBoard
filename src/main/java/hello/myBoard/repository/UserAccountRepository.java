package hello.myBoard.repository;

import hello.myBoard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUserTextId(String userTextId);
}
