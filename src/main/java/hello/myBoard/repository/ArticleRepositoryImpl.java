package hello.myBoard.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
                .orderBy(getOrderSpecifier(pageable))
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

    private OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        //Pageable 객체 정렬 조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                PathBuilder pathBuilder = new PathBuilder(article.getType(), article.getMetadata());
                return new OrderSpecifier(direction, pathBuilder.get(order.getProperty()));
            }
        }
        return null;
    }
}
