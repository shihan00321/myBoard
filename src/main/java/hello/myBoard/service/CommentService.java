package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Comment;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    public Page<CommentDto> findAllCommentsByArticle(Long articleId, Pageable pageable) {
        return commentRepository.findCommentsByArticleId(articleId, pageable).map(CommentDto::new);
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
        comment.setContent(commentRequestDto.getContent());
    }

//    @Transactional
//    public void saveComments(Long articleId, CommentRequestDto commentRequestDto) {
//        commentRepository.save(commentRequestDto.getContent());
//        Article article = articleRepository.findById(articleId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
//        Comment comment = Comment.createComment(article, commentRequestDto.getContent());
//        //article.getComments().add(comment);
//    }

    public List<CommentDto> searchComment(Long articleId) {
        return List.of(); //TODO 기능 구현
    }
}
