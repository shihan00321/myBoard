package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@DisplayName("게시글 로직")
class ArticleServiceTest {
    @InjectMocks private ArticleService articleService; //테스트 대상
    @Mock private ArticleRepository articleRepository; //테스트 대상에 의존

    @DisplayName("게시글 검색 - 리스트 반환 테스트")
    @Test
    void searchTest() {
//        Page<ArticlesDto> articleList = articleService.search(null, PageRequest.of(0, 20));
//        assertThat(articleList).isNotNull();
    }

    @DisplayName("게시글 저장")
    @Test
    void saveTest() {
        Article article = Article.createArticle(UserAccount.createUser(1L, "1234", "111@naver.com", "hye"), "title", "content", "#tag");
        BDDMockito.given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

        articleService.save(article);

        BDDMockito.then(articleRepository).should().save(ArgumentMatchers.any(Article.class));

    }

    @DisplayName("게시글 수정")
    @Test
    void updateTest() {
        Article article = Article.createArticle(UserAccount.createUser(1L, "1234", "111@naver.com", "hye"), "title", "content", "#tag");
        ArticleDetailDto articleDetailDto = new ArticleDetailDto(article);
        BDDMockito.given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

        articleService.update(1L, articleDetailDto);

        BDDMockito.then(articleRepository).should().save(ArgumentMatchers.any(Article.class));

    }

    @DisplayName("게시글 삭제")
    @Test
    void deleteTest() {
        BDDMockito.willDoNothing().given(articleRepository).delete(ArgumentMatchers.any(Article.class));

        articleService.delete(1L);

        BDDMockito.then(articleRepository).should().delete(ArgumentMatchers.any(Article.class));

    }
}