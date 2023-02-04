package shop.mtcoding.blog.controller;

import java.util.List;

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

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.dto.board.BoardDto;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;
import shop.mtcoding.blog.service.LoveService;
import shop.mtcoding.blog.service.ReplyService;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final LoveService loveService;
    private final HttpSession session;

    // main
    @GetMapping({ "/", "/board" })
    public String main(Model model) {
        List<Board> boardList = boardService.게시글불러오기();
        model.addAttribute("boardList", boardList);

        return "board/main";
    }

    // main -> detail
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        // board data (게시글 작성 username 저장)
        BoardDetailDto board = boardService.게시글상세보기(id);
        model.addAttribute("board", board);

        // reply data
        List<Reply> replyList = replyService.댓글목록불러오기();
        model.addAttribute("replyList", replyList);

        // love data

        return "board/detail";
    }

    @PostMapping("/board/insert")
    public String insert(BoardDto boardDto) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        int res1 = boardService.게시글등록(user.getId(), boardDto.getTitle(), boardDto.getContent());
        if (res1 != 1) {
            throw new CustomException("게시글 등록 실패(error-1)");
        }

        int res2 = loveService.좋아요테이블생성();
        if (res2 != 1) {
            throw new CustomException("게시글 등록 실패(error-2)");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "board/{id}/{userId}/delete", method = { RequestMethod.DELETE })
    @ResponseBody
    public String delete(@PathVariable int id, @PathVariable int userId) {
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
        if (user.getId() != userId) {
            return gson.toJson(new ResponseDto<>(-1, "권한 필요", false));
        }

        // 3. 게시글 삭제 - Service
        int res = boardService.게시글삭제하기(id);
        if (res == -2) {
            return gson.toJson(new ResponseDto<>(-2, "권한 필요", false));
        }
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-2, "DB에러", false));
        }

        return gson.toJson(new ResponseDto<>(1, "게시글 삭제 성공", true));
    }

    @RequestMapping(value = "board/{id}/{userId}/update", method = { RequestMethod.PUT })
    @ResponseBody
    public String update(@PathVariable int id, @PathVariable int userId, @RequestBody BoardDetailDto boardPut) {
        Gson gson = new Gson();
        // 1. 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return gson.toJson(new ResponseDto<>(-1, "로그인 필요", false));
        }

        // 2. 권한체크
        if (((User) session.getAttribute("principal")).getId() != userId) {
            return gson.toJson(new ResponseDto<>(-1, "권한 필요", false));
        }

        // 3. 게시글 수정 - Service
        int res = boardService.게시글수정하기(boardPut);
        if (res != -3) {
            return gson.toJson(new ResponseDto<>(-2, "게시글 존재하지 않음", false));
        }
        if (res != -2) {
            return gson.toJson(new ResponseDto<>(-2, "권한 필요", false));
        }
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-2, "DB 에러", false));
        }

        return gson.toJson(new ResponseDto<>(1, "게시글 update 성공", true));

    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        return "board/saveForm";
    }

    @GetMapping("/board/{id}/{boardUserId}/updateForm")
    public String updateForm(@PathVariable int id, @PathVariable int boardUserId, Model model) {
        // 1. 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        // 2. 권한
        if (user.getId() != boardUserId) {
            throw new CustomException("수정 권한이 없습니다");
        }

        BoardDetailDto board = boardService.게시글상세보기(id);

        model.addAttribute("board", board);
        return "board/updateForm";
    }

}
