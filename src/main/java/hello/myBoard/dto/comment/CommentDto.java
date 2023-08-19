package hello.myBoard.dto.comment;

import hello.myBoard.domain.Comment;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    //private UserAccount userAccount;
    private Long articleId;
    private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.articleId = comment.getArticle().getId();
        this.content = comment.getContent();
    }
    //dto -> entity, entity -> dto
}
