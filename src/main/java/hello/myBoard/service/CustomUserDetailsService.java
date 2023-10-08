package hello.myBoard.service;

import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.user.UserDetailDto;
import hello.myBoard.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userRepository;

    public CustomUserDetailsService(UserAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findOneByUserTextId(username)
                .map(userAccount -> createUser(username, userAccount))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String username, UserAccount user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getRoleName().name()))
                .collect(Collectors.toList());

        log.info("userId = {}", user.getId());

        return new UserDetailDto(user.getUserTextId(),
                user.getPassword(),
                grantedAuthorities, user.getId());
    }
}
