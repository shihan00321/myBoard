package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Comment;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.repository.ArticleRepository;
import hello.myBoard.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("댓글 테스트")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;
    @Mock
    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;

    @DisplayName("댓글 검색 기능 구현 테스트")
    @Test
    void test() {
        Long articleId = 1L;
        BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.createArticle("title", "content", "#tag")));
        List<CommentDto> comments = commentService.searchComment(articleId);
        assertThat(comments).isNotNull();
        BDDMockito.then(articleRepository).should().findById(articleId);
    }
}