package hello.myBoard.dto.article;

import hello.myBoard.type.SearchType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleSearchCond {
    private SearchType searchType;
    private String content;
}
