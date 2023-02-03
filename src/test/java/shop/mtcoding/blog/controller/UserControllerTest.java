package shop.mtcoding.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.blog.service.UserService;

// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest : 메모리에 다 띄워서 test
@WebMvcTest(UserController.class) // 메모리에 띄워주는 범위 : Filter, DS, C
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    // @MockBean 이렇게 가짜로 만들어야 함, Controller에 Service가 안 뜸
    // private UserService userService;

    @Test
    public void join_test() throws Exception {
        // given : 준비 데이터
        String requestBody = "username=ssar&password=1234&email=ssar@nate.com";

        // when : 잘 동작하는지 확인
        ResultActions resultActions = mvc.perform(post("/join").content(requestBody)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        // MediaType. 하면 타입이 다 나옴

        // then
        resultActions.andExpectAll(status().is3xxRedirection());
    }
}
