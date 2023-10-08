package hello.myBoard.api;

import hello.myBoard.dto.comment.CommentDto;
import hello.myBoard.dto.comment.CommentRequestDto;
import hello.myBoard.dto.user.UserDetailDto;
import hello.myBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/articles/{articleId}/comments")
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping
    public Page<CommentDto> commentsByArticle(@PathVariable Long articleId, Pageable pageable) {
        return commentService.findAllCommentsByArticle(articleId, pageable);
    }
    @PostMapping
    public void saveComments(@PathVariable Long articleId, @RequestBody CommentRequestDto commentDto, @AuthenticationPrincipal UserDetailDto userDetailDto) {
        commentService.saveComments(articleId, userDetailDto, commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long articleId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailDto userDetailDto) {
        commentService.delete(commentId, userDetailDto);
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailDto userDetailDto) {
        commentService.updateComment(commentId, commentRequestDto, userDetailDto);
    }
}
