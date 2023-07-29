package hello.myBoard.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private String tag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
