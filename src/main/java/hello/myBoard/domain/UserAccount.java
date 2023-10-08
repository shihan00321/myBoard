package hello.myBoard.domain;

import hello.myBoard.type.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "user_id"),
        @Index(columnList = "user_text_id"),
        @Index(columnList = "email"),
        @Index(columnList = "nickname"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Builder
@AllArgsConstructor
@ToString(of = {"id", "email", "nickname"})
public class UserAccount extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_text_id", unique = true, nullable = false, updatable = false, length = 20)
    private String userTextId;

    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(length = 100, unique = true)
    @Setter
    private String email;
    @Column(length = 100, unique = true)
    @Setter
    private String nickname;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.REMOVE)
    private Set<UserAccountAuthority> authorities = new HashSet<>();

    protected UserAccount() {
    }

    private UserAccount(String userTextId, String password, String email, String nickname) {
        this.userTextId = userTextId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserAccount createUser(String userTextId, String password, String email, String nickname) {
        return new UserAccount(userTextId, password, email, nickname);
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
