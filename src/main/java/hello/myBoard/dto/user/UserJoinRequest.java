package hello.myBoard.dto.user;

import lombok.Data;

@Data
public class UserJoinRequest {
    private String userId;
    private String email;
    private String nickname;
    private String password;

//    public UserAccount toEntity() {
//        return UserAccount.createUser(userId, password, email, nickname);
//    }
}
