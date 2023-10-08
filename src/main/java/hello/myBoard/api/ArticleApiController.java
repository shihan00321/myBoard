package hello.myBoard.api;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Comment;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.service.ArticleService;
import hello.myBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;
    @GetMapping
    public Page<ArticlesDto> articles(@ModelAttribute ArticleSearchCond cond, Pageable pageable) {
        return articleService.search(cond, pageable);
    }

    @PostMapping
    public Article saveArticle(@ModelAttribute Article article) {
        return articleService.save(article); //TODO 권한 인가
    }

    @GetMapping("/{articleId}")
    public ArticleDetailDto comments(@PathVariable Long articleId) {
        return articleService.findOne(articleId);
    }

    @DeleteMapping("/{articleId}")
    public void delete(@PathVariable Long articleId) {
        articleService.delete(articleId);
    }

    @PatchMapping("/{articleId}")
    public void update(@PathVariable Long articleId, @RequestBody ArticleDetailDto articleDetailDto) {
        articleService.update(articleId, articleDetailDto);
    }
}
