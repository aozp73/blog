package shop.mtcoding.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.dto.board.BoardDto;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;
import shop.mtcoding.blog.util.Script;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

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
    @ResponseBody
    public String insert(BoardDto boardDto) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return Script.href("로그인이 필요합니다", "/loginForm");
        }

        int res = boardService.게시글등록(user.getId(), boardDto.getTitle(), boardDto.getContent());
        if (res != 1) {
            return Script.back("게시글 등록 실패");
        }
        return Script.href("게시글이 등록되었습니다", "/");
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return "error/notfound";
        }

        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        // 1. 인증
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return Script.href("로그인이 필요합니다", "/loginForm");
        }

        // 2. 권한
        if (user.getId() != id) {
            return Script.href("권한이 없습니다", "/");
        }

        BoardDetailDto board = boardService.게시글상세보기(id);
        model.addAttribute("board", board);
        return "board/updateForm";
    }

}
