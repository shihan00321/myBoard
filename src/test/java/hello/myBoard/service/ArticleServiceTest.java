package hello.myBoard.service;

import hello.myBoard.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService articleService; //테스트 대상
    @Mock private ArticleRepository articleRepository; //테스트 대상에 의존

    @Test
    void serviceTest() {

    }
}