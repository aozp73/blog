package shop.mtcoding.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    // main(전체 게시물) 이동
    @GetMapping({ "/", "/board" })
    public String main(Model model) {
        List<Board> boardList = boardService.게시글불러오기();
        model.addAttribute("boardList", boardList);
        return "board/main";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        BoardDetailDto board = boardService.게시글상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @PostMapping("/board/insert")
    public String insert(BoardDto boardDto) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        int res = boardService.게시글등록(user.getId(), boardDto.getTitle(), boardDto.getContent());
        if (res != 1) {
            throw new CustomException("게시글 등록 실패");
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
            return gson.toJson(new ResponseDto<>(-1, "로그인이 필요합니다", false));
        }

        // 2. 권한체크
        if (((User) session.getAttribute("principal")).getId() != userId) {
            return gson.toJson(new ResponseDto<>(-1, "권한이 없습니다", false));
        }

        // 3. 게시글 삭제 - Service
        int res = boardService.게시글삭제하기(id);
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-1, "DB 에러", false));
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
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-1, "게시글 update 실패", false));

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

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        // 1. 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("로그인이 필요합니다");
        }

        // 2. 권한
        if (user.getId() != id) {
            throw new CustomException("권한이 없습니다");
        }

        BoardDetailDto board = boardService.게시글상세보기(id);

        model.addAttribute("board", board);
        return "board/updateForm";
    }

}
