package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.LoveService;

@RequiredArgsConstructor
@Controller
public class LoveController {
    private final HttpSession session;
    private final LoveService loveservice;

    @RequestMapping(value = "love/{boardId}/insert", method = { RequestMethod.POST })
    @ResponseBody
    public String CheckAfterInsert(@PathVariable int boardId) {
        Gson gson = new Gson();
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return gson.toJson(new ResponseDto<>(-1, "권한 필요", false));
        }

        loveservice.좋아요상태체크(boardId, principal.getId());

        return "a";
    }
}
