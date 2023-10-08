package hello.myBoard.service;

import hello.myBoard.domain.Article;
import hello.myBoard.domain.Comment;
import hello.myBoard.domain.UserAccount;
import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.dto.user.UserDetailDto;
import hello.myBoard.repository.ArticleRepository;
import hello.myBoard.repository.CommentRepository;
import hello.myBoard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserAccountRepository userAccountRepository;

    public Page<CommentDto> findAllCommentsByArticle(Long articleId, Pageable pageable) {
        return commentRepository.findCommentsByArticleId(articleId, pageable).map(CommentDto::new);
    }

    @Transactional
    public void delete(Long commentId, UserDetailDto userDetailDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if (!comment.getUserAccount().getId().equals(userDetailDto.getUserId())) {
            throw new IllegalArgumentException("삭제할 수 잇는 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailDto userDetailDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
        if (!comment.getUserAccount().getId().equals(userDetailDto.getUserId())) {
            throw new IllegalArgumentException("수정할 수 있는 권한이 없습니다.");
        }
        comment.setContent(commentRequestDto.getContent());
    }

    @Transactional
    public void saveComments(Long articleId, UserDetailDto userDetailDto, CommentRequestDto commentRequestDto) {
        UserAccount userAccount = getUserAccount(userDetailDto);
        Article article = getArticle(articleId);
        Comment comment = Comment.createComment(article, userAccount, commentRequestDto.getContent());
        comment.setArticle(article);
        commentRepository.save(comment);
    }

    private UserAccount getUserAccount(UserDetailDto userDetailDto) {
        return userAccountRepository.findById(userDetailDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private Article getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    public List<CommentDto> searchComment(Long articleId) {
        return List.of(); //TODO 기능 구현
    }
}
