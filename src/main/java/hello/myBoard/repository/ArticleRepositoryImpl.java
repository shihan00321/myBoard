package hello.myBoard.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.myBoard.domain.Article;
import hello.myBoard.domain.QComment;
import hello.myBoard.dto.article.ArticleSearchCond;
import hello.myBoard.dto.article.ArticlesDto;
import hello.myBoard.type.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static hello.myBoard.domain.QArticle.article;
import static hello.myBoard.domain.QComment.*;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory query;
    @Override
    public Page<Article> search(ArticleSearchCond cond, Pageable pageable) {

        List<Article> articles = query.
                //select(Projections.constructor(ArticlesDto.class, article)) - 1 + N 문제 발생
                select(article)
                .from(article)
                .join(article.userAccount).fetchJoin()
                .where(searchRequirement(cond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //List<ArticlesDto> articles = articleList.stream().map(ArticlesDto::new).collect(Collectors.toList());

        JPAQuery<Long> totalQuery = query.
                select(article.count())
                .from(article)
                .where(searchRequirement(cond));

        return PageableExecutionUtils.getPage(articles, pageable, totalQuery::fetchOne);
    }

    private BooleanExpression searchRequirement(ArticleSearchCond cond) {
        if (cond.getSearchType() == null || cond.getContent() == null) return null;
        SearchType searchType = cond.getSearchType();
        String content = cond.getContent();
        switch (searchType) {
            case TITLE -> {
                return article.title.contains(content);
            }
            case CONTENT -> {
                return article.content.contains(content);
            }
            case TAG -> {
                return article.tag.eq(content);
            }
            case NICKNAME -> {
                return article.userAccount.nickname.eq(content);
            }
            default -> {
                return null;
            }
        }
    }

    private BooleanExpression titleLike(String title) {
        return StringUtils.hasText(title) ? article.title.contains(title) : null;
    }

    private BooleanExpression tagEq(String tag) {
        return StringUtils.hasText(tag) ? article.tag.eq(tag) : null;
    }

    private BooleanExpression contentLike(String content) {
        return StringUtils.hasText(content) ? article.content.contains(content) : null;
    }

    private BooleanExpression createdByEq(String createdBy) {
        return StringUtils.hasText(createdBy) ? article.createdBy.eq(createdBy) : null;
    }


}
