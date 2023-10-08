package hello.myBoard.api;

import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.user.UserAccountDto;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.dto.user.UserSignUpResponseDto;
import hello.myBoard.dto.user.UserUpdateDto;
import hello.myBoard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserApiController {
    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public void signUp(@RequestBody UserJoinRequest joinUserDto) {
        userAccountService.join(joinUserDto);
    }

    @PatchMapping("/members/{userId}")
    public void updateMember(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto, @AuthenticationPrincipal User userAccount) {
        log.info("userAccount = {}", userAccount);
        userAccountService.updateUser(userId, userUpdateDto, userAccount);
    }

    @DeleteMapping("/members/{userId}")
    public void deleteUser(@PathVariable Long userId, @AuthenticationPrincipal User userAccount) {
        userAccountService.deleteUser(userId, userAccount);
    }
}
