package hello.myBoard.dto.comment;

import hello.myBoard.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long commentId;
    private String nickname;
    private Long articleId;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;

    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.articleId = comment.getArticle().getId();
        this.content = comment.getContent();
        this.createdBy = comment.getCreatedBy();
        this.createdAt = comment.getCreatedAt();
        this.nickname = comment.getUserAccount().getNickname();
    }
}
