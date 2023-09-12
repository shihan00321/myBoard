package hello.myBoard.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.type.RoleType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpResponseDto {
    private String userTextId;
    private String password;
    private String nickname;

    private RoleType role;

    public static UserSignUpResponseDto from(UserAccount user) {
        if(user == null) return null;

        return UserSignUpResponseDto.builder()
                .userTextId(user.getUserTextId())
                .nickname(user.getNickname())
                .role(user.getRoleType())
                .build();
    }
}
