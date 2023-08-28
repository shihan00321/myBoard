package hello.myBoard.controller;

import hello.myBoard.dto.article.ArticleDetailDto;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.service.ArticleService;
import hello.myBoard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(@ModelAttribute ArticleSearchCond cond,
                           ModelMap model,
                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticlesDto> search = articleService.search(cond, pageable);
        List<Integer> paginationBar = paginationService.getPaginationNumbers(pageable.getPageNumber(), search.getTotalPages());

        model.addAttribute("articles", search);
        model.addAttribute("paginationBar", paginationBar);

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap model) {
        ArticleDetailDto articleDetailDto = articleService.findOne(articleId);
        model.addAttribute("article", articleDetailDto);
        return "articles/detail";
    }
}
