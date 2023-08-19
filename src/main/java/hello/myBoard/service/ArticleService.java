package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Page<ArticlesDto> findAllArticles(Pageable pageable) {
        return articleRepository.findAllArticlesBy(pageable).map(article -> new ArticlesDto(
                article.getId(),
                article.getTitle(),
                article.getTag(),
                article.getCreatedBy(),
                article.getCreatedAt()
        ));
    }

    public ArticleDetailDto findOne(Long id) {
        Article findArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return new ArticleDetailDto(findArticle);
    }
}
