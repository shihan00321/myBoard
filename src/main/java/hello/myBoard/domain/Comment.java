package hello.myBoard.domain;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Article article; //게시글 (Id);
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
