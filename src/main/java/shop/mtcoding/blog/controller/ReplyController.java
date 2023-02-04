package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.ReplyService;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final HttpSession session;

    private final ReplyService replyService;

    // 댓글 insert
    // @PostMapping("/reply/insert")
    @RequestMapping(value = "/reply/insert", method = { RequestMethod.POST })
    @ResponseBody
    public String insert(@RequestBody Reply reply) {
        Gson gson = new Gson();
        // 유효성 검사
        if (reply.getContent() == null || reply.getContent().isEmpty()) {
            return gson.toJson(new ResponseDto<>(-1, "댓글 공백", false));
        }

        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return gson.toJson(new ResponseDto<>(-1, "로그인 필요", false));
        }
        // reply DB insert - Service
        int res = replyService.댓글쓰기(reply);
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-1, "댓글 - DB insert 실패", false));
        }

        return gson.toJson(new ResponseDto<>(1, "댓글 - insert 성공", true));
    }
}
