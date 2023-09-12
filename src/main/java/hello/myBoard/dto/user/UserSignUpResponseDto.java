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
    private Set<AuthorityDto> authorityDtoSet;

    public static UserSignUpResponseDto from(UserAccount userAccount) {
        if(userAccount == null) return null;

        return UserSignUpResponseDto.builder()
                .userTextId(userAccount.getUserTextId())
                .nickname(userAccount.getNickname())
                .authorityDtoSet(userAccount.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority().getRoleName().name()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
