package hello.myBoard.controller;

import hello.myBoard.config.SpringSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@Import(SpringSecurityConfig.class)
@WebMvcTest(HomeController.class)
class HomeControllerTest {
    private final MockMvc mvc;

    public HomeControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void redirectingHomeTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}