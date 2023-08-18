package hello.myBoard.controller;

import hello.myBoard.config.SpringSecurityConfig;
import hello.myBoard.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * WebMvcTest
 * @Controller @ControllerAdvice, @JsonComponent ... 등 웹과 관련된 빈만 주입
 * @Service는 주입되지 않아 컨트롤러 생성에 실패
 */
@WebMvcTest(ArticleController.class)
@Import(SpringSecurityConfig.class)
public class AuthControllerTest {

     private final MockMvc mvc;
//     @MockBean
//     private ArticleService articleService;

     @Autowired
    public AuthControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }
}
