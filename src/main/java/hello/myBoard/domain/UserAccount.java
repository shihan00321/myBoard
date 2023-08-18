package hello.myBoard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Getter
@ToString(of = {"id", "email", "nickname"})
public class UserAccount extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 30)
    private Long id;
    @Column(name = "user_password", nullable = false, length = 20)
    private String password;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String nickname;

    protected UserAccount() {
    }

    public UserAccount(Long id, String password, String email, String nickname) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserAccount of(Long id, String password, String email, String nickname) {
        return new UserAccount(id, password, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(getId(), that.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
