package hello.myBoard.domain;

import hello.myBoard.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "authority_role_name")
    private RoleType roleName;
}
