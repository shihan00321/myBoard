package hello.myBoard.domain;

import hello.myBoard.dto.comment.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(of = {"id", "content"})
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article; //게시글(Id), FK;

    @Column(nullable = false, length = 500)
    @Setter
    private String content;

    //==연관 관계 메서드==//
    public void setArticle(Article article) {
        this.article = article;
        article.getComments().add(this);
    }

    protected Comment() {
    }


    private Comment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public void deletePost(Article article) {
        this.article = null;
    }

    public static Comment createComment(Article article, String content) {
        return new Comment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
