package hello.myBoard.dto.article;

import hello.myBoard.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticlesDto {
    private Long articleId;
    //private Long userId;
    private String title;
    private String tag;
    private String createdBy;
    private LocalDateTime createdAt;

    public ArticlesDto(Article article) {
        this.articleId = article.getId();
        //this.userId = article.getUserAccount().getId();
        this.title = article.getTitle();
        this.tag = article.getTag();
        this.createdBy = article.getCreatedBy();
        this.createdAt = article.getCreatedAt();
    }
}
