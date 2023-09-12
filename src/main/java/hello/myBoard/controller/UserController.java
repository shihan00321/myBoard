package hello.myBoard.controller;

import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserAccountService userAccountService;
    @GetMapping("/signup")
    public String signUpPage() {
        return "loginPage";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserJoinRequest joinUserDto) {
        userAccountService.join(joinUserDto);
        log.info("UserAccountController signup");
        return "ok";
    }



    @PostMapping("/login")
    public void login() {
        //
    }
}
