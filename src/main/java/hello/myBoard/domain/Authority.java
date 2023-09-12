package hello.myBoard.domain;

import hello.myBoard.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "authority_id")
//    private Long id;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "authority_role")
    private RoleType roleName;
}
