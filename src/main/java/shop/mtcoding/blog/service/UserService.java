package shop.mtcoding.blog.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final HttpSession session;
    private final UserRepository userRepository;

    public int 회원가입(JoinReqDto joinReqDto) {
        // username 체크 해야 함 (이렇게 layer 분리하는 이유는 책임을 나누어 디버깅에 간편)

        // user_tb 반영 (insert)
        int res = userRepository.insert(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getPassword());
        if (res != 1) {
            return -1;
        }
        return 1;
    }

    public int 로그인(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return -1;
        }
        session.setAttribute("principal", user);
        return 1;
    }
}
