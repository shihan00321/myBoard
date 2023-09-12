package hello.myBoard.type;

import lombok.Getter;

@Getter
public enum RoleType {
    USER("사용자"),
    ADMIN("관리자");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
}
