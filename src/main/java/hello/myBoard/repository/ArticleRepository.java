package hello.myBoard.repository;

import hello.myBoard.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom{
    //Page<Article> findAllArticlesBy(Pageable pageable);
}
