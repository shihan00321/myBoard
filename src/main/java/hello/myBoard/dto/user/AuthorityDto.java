package hello.myBoard.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorityDto {
    private String authorityName;
}
