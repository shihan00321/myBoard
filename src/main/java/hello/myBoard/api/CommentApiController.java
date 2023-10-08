package hello.myBoard.api;

import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.dto.user.UserDetailDto;
import hello.myBoard.service.ArticleService;
import hello.myBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/articles/{articleId}/comments")
public class CommentApiController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    public Page<CommentDto> commentsByArticle(@PathVariable Long articleId, Pageable pageable, @AuthenticationPrincipal UserDetailDto userAccount) {
        log.info("userAccount = {}", userAccount.getUserId());
        return commentService.findAllCommentsByArticle(articleId, pageable);
    }
    @PostMapping
    public void saveComments(@PathVariable Long articleId, @RequestBody CommentRequestDto commentDto, @AuthenticationPrincipal User userAccount) {
        articleService.saveComments(articleId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        articleService.delete(commentId);
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(commentId, commentRequestDto);
    }
}
