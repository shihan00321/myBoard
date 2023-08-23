package hello.myBoard.repository;

import hello.myBoard.domain.Article;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<Article> search(ArticleSearchCond articleSearchCond, Pageable pageable);
}
