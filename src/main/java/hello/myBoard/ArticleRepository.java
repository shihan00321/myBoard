package hello.myBoard;

import hello.myBoard.domain.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Article article) {
        em.persist(article);
        return article.getId();
    }

    public Article find(Long id) {
        return em.find(Article.class, id);
    }
}
