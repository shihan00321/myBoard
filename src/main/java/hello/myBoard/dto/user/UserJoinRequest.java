package hello.myBoard.dto.user;

import hello.myBoard.domain.UserAccount;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserJoinRequest {
    private String userId;
    private String email;
    private String nickname;
    private String password;

    public UserAccount toEntity() {
        return UserAccount.createUser(userId, password, email, nickname);
    }
}
