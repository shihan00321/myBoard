package hello.myBoard.api;

import hello.myBoard.domain.Article;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.service.ArticleService;
import hello.myBoard.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleApiControllerTest {
    @Autowired private ArticleService articleService;
    @Autowired private CommentService commentService;

    @Test
    void updateTest() {
        ArticleDetailDto articleDetailDto = new ArticleDetailDto(Article.createArticle("hello", "hello2", "#spring"));
        articleService.update(10L, articleDetailDto);
    }

    @Test
    @Rollback(value = false)
    void saveCommentTest() {
        CommentRequestDto requestDto = new CommentRequestDto("myContent 댓글1");
        articleService.saveComments(10L, requestDto);
    }

    @Test
    @Rollback(value = false)
    void deleteComment() {
        commentService.delete(40L);
    }
}