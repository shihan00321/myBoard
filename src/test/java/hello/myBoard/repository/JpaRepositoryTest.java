package hello.myBoard.repository;

import hello.myBoard.config.JpaConfig;
import hello.myBoard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("select test")
    @Test
    void selectTest() {
        List<Article> articles = articleRepository.findAll();
        assertThat(articles)
                .isNotNull()
                .hasSize(7);
    }

    @DisplayName("insert test")
    @Test
    void insertTest() {
        Article article = Article.createArticle("title", "content", "#tag");

        Article savedArticle = articleRepository.save(article);

        assertThat(article).isEqualTo(savedArticle);
    }

    @DisplayName("update test")
    @Test
    void updateTest() {
        Article article = articleRepository.findById(1L).orElseThrow();
        article.setTag("#tag2");
        Article savedArticle = articleRepository.save(article); //병합
        assertThat(savedArticle.getTag()).isEqualTo("#tag2");
    }

    @DisplayName("delete test")
    @Test
    void deleteTest() {
        articleRepository.deleteById(1L);
        assertThat(articleRepository.findAll().size()).isEqualTo(6);
    }

}