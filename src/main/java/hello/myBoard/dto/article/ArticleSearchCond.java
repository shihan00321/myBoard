package hello.myBoard.dto.article;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleSearchCond {
    private String title;
    private String tag;
    private String content;
    private String createdBy;
}
