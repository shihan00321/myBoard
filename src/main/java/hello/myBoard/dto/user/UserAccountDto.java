package hello.myBoard.dto.user;

import hello.myBoard.domain.UserAccount;
import lombok.Data;

@Data
public class UserAccountDto {
    private Long userId;
    private String nickName;

    public UserAccountDto(UserAccount userAccount) {
        this.userId = userAccount.getId();
        this.nickName = userAccount.getNickname();
    }
}
