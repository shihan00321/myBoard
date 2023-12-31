package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Comment;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.dto.user.UserDetailDto;
import hello.myBoard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Page<ArticlesDto> search(ArticleSearchCond cond, Pageable pageable) {
        if (cond.getSearchType() == null || cond.getContent() == null) {
            return articleRepository.findAll(pageable).map(ArticlesDto::new);
        }
        Page<Article> articleList = articleRepository.search(cond, pageable);
        return articleList.map(ArticlesDto::new);
    }

    public ArticleDetailDto findOne(Long id) {
        Article findArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return new ArticleDetailDto(findArticle);
    }

    @Transactional
    public void delete(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        List<Comment> comments = article.getComments();
        for (Comment comment : comments) {
            comment.deletePost(article);
        }
        articleRepository.delete(article);
    }

    @Transactional
    public void update(Long articleId, ArticleDetailDto articleDetailDto) {
        Article findArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        findArticle.setTitle(articleDetailDto.getTitle());
        findArticle.setTag(articleDetailDto.getTag());
        findArticle.setContent(articleDetailDto.getContent());
    }

}
