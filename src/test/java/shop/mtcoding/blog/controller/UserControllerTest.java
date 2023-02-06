package shop.mtcoding.blog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.blog.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void login_test() throws Exception {
        // given
        // 통신으로 들어가는 모든 데이터는 문자열
        String requestBody = "username=ssar&password=1234";

        // when
        // mvc.perform : Controller 때릴 수 있음
        // Post는 바디가 있으므로 타입을 적어줘야함
        // .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        // -> JSON으로 바꾸면 요청하는게 www mimeType이므로 오류 발생

        ResultActions resultActions = mvc.perform(post("/login").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // resultActions : 응답의 정보를 다 가지고 있음 (request, repsones 기록 다 있음)
        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        System.out.println(principal.getUsername());

        // then
        assertThat(principal.getUsername()).isEqualTo("ssar");
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void join_test() throws Exception {
        // given
        String requestBody = "username=ssar&password=1234&email=ssar@nate.com";

        // when
        ResultActions resultActions = mvc.perform(post("/join").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        // then
        // resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(status().is3xxRedirection());
    }
}