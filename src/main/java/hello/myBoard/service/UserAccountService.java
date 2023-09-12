package hello.myBoard.service;

import hello.myBoard.domain.Authority;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.domain.UserAccountAuthority;
import hello.myBoard.dto.user.UserAccountDto;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.dto.user.UserSignUpResponseDto;
import hello.myBoard.repository.AuthorityRepository;
import hello.myBoard.repository.UserAccountAuthorityRepository;
import hello.myBoard.repository.UserAccountRepository;
import hello.myBoard.type.RoleType;
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

    public void join(UserJoinRequest userJoinRequest) {

        if (userAccountRepository.findOneByUserTextId(userJoinRequest.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저.");
        }
        if (userJoinRequest.getPassword().length() > 20) {
            throw new RuntimeException("비밀번호 20글자 초과");
        }

        Authority authority = Authority.builder()
                .roleName(RoleType.ROLE_USER)
                .build();

        UserAccount user = UserAccount
                .createUser(
                        userJoinRequest.getUserId(),
                        passwordEncoder.encode(userJoinRequest.getPassword()),
                        userJoinRequest.getEmail(),
                        userJoinRequest.getNickname()
                        );

        UserAccountAuthority userAccountAuthority = UserAccountAuthority.builder()
                .authority(authority)
                .build();

        userAccountAuthority.setUserAccount(user);

        userAccountRepository.save(user);

    }
}
