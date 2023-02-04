package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "reply/{boardId}/{replyId}/{boardUserId}/{replyUserId}/delete", method = {
            RequestMethod.DELETE })
    @ResponseBody
    public String delete(@PathVariable int boardId, @PathVariable int replyId, @PathVariable int boardUserId,
            @PathVariable int replyUserId) {
        /*
         * !! 추가작업 필요 : DB log기록
         */
        Gson gson = new Gson();

        // 1. 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return gson.toJson(new ResponseDto<>(-1, "로그인 필요", false));
        }
        // 2. 권한체크
        if (((User) session.getAttribute("principal")).getId() != replyUserId) {
            return gson.toJson(new ResponseDto<>(-1, "권한 필요", false));
        }

        // 3. 댓글 삭제 - Service
        int res = replyService.댓글삭제하기(boardId, replyId, boardUserId, replyUserId);
        if (res == -2) {
            return gson.toJson(new ResponseDto<>(-2, "DB오류", false));
        }

        if (res == -1) {
            return gson.toJson(new ResponseDto<>(-2, "권한 필요", false));
        }

        return gson.toJson(new ResponseDto<>(1, "댓글 - delete 성공", true));
    }

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
            return gson.toJson(new ResponseDto<>(-2, "댓글 - DB insert 실패", false));
        }

        return gson.toJson(new ResponseDto<>(1, "댓글 - insert 성공", true));
    }
}
