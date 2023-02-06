package shop.mtcoding.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import shop.mtcoding.blog.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    // Autowired 안 됨

    private MockHttpSession mockSession;

    @BeforeEach // Test 메서드 실행 직전 마다 실행 됨
    public void setUp() {
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        // localDataTime이 현재시간을 뽑아주는데 Timestamp는 이걸 못함 따라서 아래 코드 작성
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void save_test() throws Exception {
        // given

        // 가짜 session 생성 되었음
        setUp();

        String requestBody = "title=제목1&content=내용1";

        // when
        ResultActions resultActions = mvc.perform(
                post("/board").content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .session(mockSession));

        // then
        // resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(status().is3xxRedirection());
    }
}