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
import shop.mtcoding.blog.dto.love.LoveDto;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;
import shop.mtcoding.blog.service.LoveService;

@RequiredArgsConstructor
@Controller
public class LoveController {
    private final HttpSession session;
    private final LoveService loveService;
    private final BoardService boardService;

    @RequestMapping(value = "love/{boardId}/insert", method = { RequestMethod.POST })
    @ResponseBody
    public String CheckAfterInsert(@PathVariable int boardId) {
        Gson gson = new Gson();
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return gson.toJson(new ResponseDto<>(-1, "권한 필요", false));
        }

        // 최초 좋아요 한적 없는 게시물이면 love 테이블 생성
        int res1 = loveService.좋아요테이블체크(boardId, principal.getId());
        if (res1 != 1) {
            return gson.toJson(new ResponseDto<>(-1, "테이블 생성 실패", false));
        }

        // 클릭 시 love 테이블 isCheck 값이 false,true 반대로 설정
        LoveDto loveDto = loveService.좋아요isCheck세팅(boardId, principal.getId());
        if (loveDto.getCode() != 1) {
            return gson.toJson(new ResponseDto<>(-1, "isCheck 세팅 실패", false));
        }

        // board table 좋아요 갯수 반영
        int res2 = boardService.좋아요반영(boardId, loveDto);
        if (res2 != 1) {
            return gson.toJson(new ResponseDto<>(-1, "boardtable 좋아요세팅 실패", false));
        }
        return gson.toJson(loveDto);
    }
}
