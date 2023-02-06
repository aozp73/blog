package shop.mtcoding.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.user.UserCheckRes;
import shop.mtcoding.blog.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.model.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public int 유저정보수정(int id, String password, String email) {
        User user = userRepository.findById(id);
        if (user == null) {
            return -1;
        }

        if (user.getId() != id) {
            return -1;
        }

        int res = userRepository.updateById(id, password, email);
        if (res != 1) {
            return -1;
        }

        return 1;

    }

    public User 유저한명(int id) {
        User user = userRepository.findById(id);
        return user;
    }

    public int 유저네임중복체크(String username) {
        List<UserCheckRes> usernameList = userRepository.findByAllUsername();
        for (UserCheckRes userCheckRes : usernameList) {
            if (userCheckRes.getUsername().equals(username)) {
                return -1;
            }
        }

        return 1;
    }

    // insert하나라도 a가 회원가입할 때 b가 회원가입 요청할 때 (b 쓰레드가 멍때리게 됨)
    // 메서드 자체는 동시에 때려 짐 (메서드 public 앞에 Sysn-- 적으면 동기화 할 수는 있음)
    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        // username 체크 해야 함 (이렇게 layer 분리하는 이유는 책임을 나누어 디버깅에 간편)
        User sameUser = userRepository.findByUsername(joinReqDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }

        // user_tb 반영 (insert)
        int res = userRepository.insert(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getPassword());
        if (res != 1) {
            throw new CustomException("회원가입 실패");
            // return Script.back("회원정보를 다시 확인 해주세요.");
        }
    }

    // public void 변경() {
    // userRepository.updateById(1, "hello", "hello@naver.com");

    // }

    // Transactional 내 Transactional이 끝나기전 까지 그전에 봤던 데이터를 기억하고 있음
    // 한번봤던 데이터를 계속 기억하게 됨 (그래서 select도 보통 걸게 됨)
    // 데이터를 보고 통계를 뽑고 할 때 한 번 봤는데 중간에 연산되고 있을 때 데이터가 변경 되면 꼬이게 됨
    @Transactional(readOnly = true)
    public User 로그인(LoginReqDto loginReqDto) {
        User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());
        if (principal == null) {
            throw new CustomException("유저네임 혹은 패스워드가 잘못 입력되었습니다");
        }
        return principal;
    }

    // public int 로그인(String username, String password) {
    // User user = userRepository.findByUsernameAndPassword(username, password);

    // if (user == null) {
    // return -1;
    // }
    // session.setAttribute("principal", user);
    // return 1;
    // }
}
