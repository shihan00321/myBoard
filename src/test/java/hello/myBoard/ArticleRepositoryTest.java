package hello.myBoard;

import hello.myBoard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired ArticleRepository articleRepository;

    @Test
    @Transactional
    void testArticle() {
        Article article = new Article();
        article.setContent("hi");
        article.setTitle("hello");
        article.setCreatedBy("s");
        article.setTag("tag");
        article.setModifiedBy("n");

        Long savedId = articleRepository.save(article);
        Article findArticle = articleRepository.find(savedId);
        Assertions.assertThat(findArticle.getId()).isEqualTo(article.getId());

    }
}