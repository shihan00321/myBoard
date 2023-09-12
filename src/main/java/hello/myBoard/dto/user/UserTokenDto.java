package hello.myBoard.dto.user;

import lombok.Data;

@Data
public class UserTokenDto {
    private String token;

    public UserTokenDto(String token) {
        this.token = token;
    }
}
