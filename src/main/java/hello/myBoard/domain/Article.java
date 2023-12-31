package hello.myBoard.domain;

import hello.myBoard.dto.user.UserAccountDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString(of = {"id", "title", "content", "tag"})
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "tag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private UserAccount userAccount;

    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;
    @Setter private String tag;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt desc")
    private final List<Comment> comments = new ArrayList<>();

    protected Article() {
    }

    private Article(UserAccount userAccount, String title, String content, String tag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    //==생성 메서드==//
    public static Article createArticle(UserAccount userAccount, String title, String content, String tag) {
        return new Article(userAccount, title, content, tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
