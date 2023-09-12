package hello.myBoard.service;

import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.user.UserAccountDto;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.dto.user.UserSignUpResponseDto;
import hello.myBoard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignUpResponseDto join(UserJoinRequest userJoinRequest) {

        if (userAccountRepository.findOneByUserTextId(userJoinRequest.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저.");
        }
        if (userJoinRequest.getPassword().length() > 20) {
            throw new RuntimeException("비밀번호 20글자 초과");
        }

        UserAccount user = UserAccount
                .createUser(
                        userJoinRequest.getUserId(),
                        passwordEncoder.encode(userJoinRequest.getPassword()),
                        userJoinRequest.getEmail(),
                        userJoinRequest.getNickname()
                        );

        return UserSignUpResponseDto.from(userAccountRepository.save(user));
    }
}
