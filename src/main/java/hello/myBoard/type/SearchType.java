package hello.myBoard.type;

import lombok.Getter;

@Getter
public enum SearchType {
    TITLE("제목"), CONTENT("내용"), NICKNAME("작성자"), TAG("태그");

    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
