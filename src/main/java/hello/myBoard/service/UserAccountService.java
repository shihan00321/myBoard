package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Authority;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.domain.UserAccountAuthority;
import hello.myBoard.dto.user.UserAccountDto;
import hello.myBoard.dto.user.UserJoinRequest;
import hello.myBoard.dto.user.UserSignUpResponseDto;
import hello.myBoard.dto.user.UserUpdateDto;
import hello.myBoard.repository.AuthorityRepository;
import hello.myBoard.repository.UserAccountAuthorityRepository;
import hello.myBoard.repository.UserAccountRepository;
import hello.myBoard.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountAuthorityRepository userAccountAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserJoinRequest userJoinRequest) {

        if (userAccountRepository.findOneByUserTextId(userJoinRequest.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저.");
        }
        verificationPassword(userJoinRequest);

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
        userAccountAuthorityRepository.save(userAccountAuthority);
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateDto userUpdateDto, User user) {
        UserAccount userAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        if (user.getUsername().equals(userAccount.getUserTextId())) {
            userAccount.setEmail(userUpdateDto.getEmail());
            userAccount.setNickname(userUpdateDto.getNickname());
        }
        else throw new RuntimeException("회원을 수정할 권한이 없습니다.");
    }

    @Transactional(readOnly = true)
    public void deleteUser(Long userId, User user) {
        UserAccount userAccount = userAccountRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다.")); //TODO deleteById에서 유저를 조회함 Select 쿼리가 2번 나가는 문제 발생, userId를 받아오는 방법?
        if (user.getUsername().equals(userAccount.getUserTextId())) {
            userAccountRepository.deleteById(userId);
        }
        else throw new RuntimeException("회원을 삭제할 권한이 없습니다.");
    }


    private static void verificationPassword(UserJoinRequest userJoinRequest) {
        if (userJoinRequest.getPassword().length() > 20) {
            throw new RuntimeException("비밀번호 20글자 초과");
        }
    }
}
