package hello.myBoard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * UserAccount : Authority -> N:M
 * 다대다 매핑을 위한 중간 테이블
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_role_name")
    private Authority authority;

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        userAccount.getAuthorities().add(this);
    }
}
