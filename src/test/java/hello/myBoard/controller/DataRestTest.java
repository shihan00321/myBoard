package hello.myBoard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Disabled("통합테스트이므로 제외")
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    @Autowired
    public DataRestTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("api 게시글 모두 조회")
    @Test
    void findAllArticleApiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 게시글 하나 조회")
    @Test
    void findOneArticleApiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/articles/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 게시글 - 댓글 리스트 조회")
    @Test
    void findAllArticleCommentApiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/articles/1/comments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 전체 댓글 리스트 조회")
    @Test
    void findAllCommentApiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/comments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("api 댓글 하나 조회")
    @Test
    void findOneCommentApiTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/comments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
