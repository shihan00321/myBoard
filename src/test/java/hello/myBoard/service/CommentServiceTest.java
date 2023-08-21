package hello.myBoard.service;

import hello.myBoard.domain.Comment;
import hello.myBoard.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired private CommentRepository commentRepository;

    @Test
    void test() {
        Page<Comment> commentsByArticleId = commentRepository.findCommentsByArticleId(10L, PageRequest.of(0, 10));
        for (Comment comment : commentsByArticleId) {
            System.out.println("comment = " + comment);
        }
    }
}