package hello.myBoard.api;

import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.user.UserAccountDto;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.dto.user.UserSignUpResponseDto;
import hello.myBoard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserJoinRequest joinUserDto) {
        userAccountService.join(joinUserDto);
    }
}
