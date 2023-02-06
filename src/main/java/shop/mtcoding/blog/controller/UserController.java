package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blog.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.blog.dto.user.UserUpdateReq;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.UserService;

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

        userService.회원가입(joinReqDto);
        // return "redirect:/loginForm";

        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    // @ResponseBody
    public String login(LoginReqDto loginReqDto) {

        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해주세요");
        }

        User principal = userService.로그인(loginReqDto);

        session.setAttribute("principal", principal);

        return "redirect:/";

        // int res = userService.로그인(loginReqDto.getUsername(),
        // loginReqDto.getPassword());
        // if (res != 1) {
        // return Script.back("아이디와 비밀번호를 다시 확인해주세요");
        // }

        // return Script.href("/");
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @RequestMapping(value = "user/{id}/update", method = { RequestMethod.PUT })
    @ResponseBody
    public ResponseDto<?> update(@PathVariable int id, @RequestBody UserUpdateReq user) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return new ResponseDto<>(-1, "로그인이 필요합니다", false);
        }

        if (principal.getId() != id) {
            return new ResponseDto<>(-1, "권한이 없습니다", true);
        }

        // userTb update
        int res = userService.유저정보수정(id, user.getPassword(), user.getEmail());
        if (res != 1) {
            return new ResponseDto<>(-1, "정보수정 실패 (고객센터에 문의해주세요)", true);
        }

        return new ResponseDto<>(1, "회원수정 완료", true);
    }

    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        if (principal.getId() != id) {
            throw new CustomException("권한이 없습니다");
        }

        User user = userService.유저한명(principal.getId());
        model.addAttribute("user", user);

        return "/user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
