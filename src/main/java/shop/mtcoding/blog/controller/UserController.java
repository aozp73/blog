package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.service.UserService;
import shop.mtcoding.blog.util.Script;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/user/usernameSameCheck")
    public @ResponseBody ResponseDto<?> usernameCheck(String username) {
        if (username == null || username.isEmpty()) {
            return new ResponseDto<>(-1, "username 공백", null);
        }

        // userService
        int res = userService.유저네임중복체크(username);

        if (res == -1) {
            return new ResponseDto<>(-1, "동일한 아이디가 존재합니다", false);
        }

        return new ResponseDto<>(1, "해당 아이디로 회원가입 가능합니다", true);
    }

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {

        // 이렇게 까지 하고 이것 관련 문제가 터지면 DB문제라고 단정 가능
        // DB쪽 쿼리만 볼 수 있음
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }

        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }

        if (joinReqDto.getPasswordCheck() == null || joinReqDto.getPasswordCheck().isEmpty()) {
            throw new CustomException("passwordCheck를 작성해주세요");
        }

        if (!(joinReqDto.getPassword().equals(joinReqDto.getPasswordCheck()))) {
            throw new CustomException("비밀번호가 일치하지 않습니다");
        }

        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }

        int res = userService.회원가입(joinReqDto);
        // return "redirect:/loginForm";
        if (res != 1) {
            throw new CustomException("회원가입 실패");
            // return Script.back("회원정보를 다시 확인 해주세요.");
        }
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        int res = userService.로그인(username, password);
        if (res != 1) {
            return Script.back("아이디와 비밀번호를 다시 확인해주세요");
        }

        return Script.href("/");
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String update() {
        return "/user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
