package hello.myBoard.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.myBoard.dto.user.UserDetailDto;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return () -> Optional.ofNullable(((UserDetailDto) authentication.getPrincipal()).getUsername());
        return () -> Optional.of(UUID.randomUUID().toString()); //todo 인증 정보로 수행
    }
}
