package hello.myBoard.dto.article;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.user.UserAccountDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ArticlesDto {
    private Long articleId;
    private String nickname;
    private String title;
    private String content;
    private List<CommentDto> comments;
    private String tag;
    private String createdBy;
    private LocalDateTime createdAt;

    public ArticlesDto(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.comments = article.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        this.tag = article.getTag();
        this.createdBy = article.getCreatedBy();
        this.createdAt = article.getCreatedAt();
        this.nickname = article.getUserAccount().getNickname();
    }
}
