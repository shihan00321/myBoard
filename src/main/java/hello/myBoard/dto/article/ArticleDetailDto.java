package hello.myBoard.dto.article;

import hello.myBoard.domain.Article;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.user.UserAccountDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ArticleDetailDto {
    private Long id;
    private String title;
    private String content;
    private String tag;
    private String nickname;
    private String email;
    private List<CommentDto> comments;
    private LocalDateTime createdAt;
    private String createdBy;

    public ArticleDetailDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.tag = article.getTag();
        this.comments = article.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        this.createdAt = article.getCreatedAt();
        this.createdBy = article.getCreatedBy();
        this.nickname = article.getUserAccount().getNickname();
        this.email = article.getUserAccount().getEmail();
    }
}
