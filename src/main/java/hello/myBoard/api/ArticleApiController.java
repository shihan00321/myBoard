package hello.myBoard.api;

import hello.myBoard.domain.Article;
import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleService articleService;
    @GetMapping("/articles")
    public Page<ArticlesDto> articles(Pageable pageable) {
        return articleService.findAllArticles(pageable);
    }

    @PostMapping("/articles")
    public Article saveArticle(@ModelAttribute Article article) {
        return articleService.save(article); //TODO 권한 인가
    }

    @GetMapping("/articles/{articleId}")
    public ArticleDetailDto comments(@PathVariable Long articleId) {
        return articleService.findOne(articleId);
    }
}
