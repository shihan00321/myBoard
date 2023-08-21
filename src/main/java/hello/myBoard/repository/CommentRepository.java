package hello.myBoard.repository;

import hello.myBoard.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findCommentsByArticleId(Long articleId, Pageable pageable);
}
