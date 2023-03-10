package shop.mtcoding.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
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
import shop.mtcoding.blog.dto.board.BoardSearchDto;
import shop.mtcoding.blog.dto.board.PageDto;
import shop.mtcoding.blog.dto.board.ResponseDto;
import shop.mtcoding.blog.dto.love.LoveDto;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.service.BoardService;
import shop.mtcoding.blog.service.LoveService;
import shop.mtcoding.blog.service.ReplyService;
import shop.mtcoding.blog.vo.Criteria;

@RequiredArgsConstructor
@Controller
public class BoardController {
    Gson gson = new Gson();

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final ReplyService replyService;
    private final LoveService loveService;
    private final HttpSession session;

    @GetMapping("/board/search")
    public @ResponseBody String serarching(String serachKeyword) {
        List<Board> boardSearchlist = boardRepository.findSearchContent(serachKeyword);
        return gson.toJson(boardSearchlist);
    }

    @RequestMapping(value = "board/searchPaging", method = { RequestMethod.POST })
    public @ResponseBody String searchPaging(@RequestBody BoardSearchDto boardSearchDto, Model model) {
        Gson gson = new Gson();

        // Service ?????? ??????
        List<Board> boardSearchlist = boardRepository.findSearchContent(boardSearchDto.getKeyword());
        int total = boardSearchlist.size();

        int begin = boardSearchDto.getBegin();
        int end = boardSearchDto.getEnd();
        int cnt = total / 12;
        int calRemain = total % 12;

        if (begin == cnt * 12) {
            end = begin + calRemain - 1;
        }

        List<Board> newList = new ArrayList<>();

        for (int i = begin; i <= end; i++) {
            newList.add(boardSearchlist.get(i));
        }

        return gson.toJson(newList);
    }

    @GetMapping("/board/paging")
    public @ResponseBody String paging(int begin, int end, Model model) {
        Gson gson = new Gson();
        int cnt = boardService.??????????????????????????????() / 12;
        int calRemain = boardService.??????????????????????????????() % 12;

        if (begin == cnt * 12) {
            end = begin + calRemain - 1;
        }

        // Service ?????? ??????
        List<Board> boardList = boardRepository.findByAllOrederByLove();
        List<Board> newList = new ArrayList<>();

        for (int i = begin; i <= end; i++) {
            newList.add(boardList.get(i));
        }

        return gson.toJson(newList);
    }

    // main
    @GetMapping({ "/", "/board" })
    public String main(Criteria cri, Model model) {
        List<Board> boardList = boardService.?????????????????????();
        model.addAttribute("boardList", boardList);

        // List<BoardVO> board = boardService.getList(cri);
        // for (BoardVO BoardVO : board) {
        // System.out.println(BoardVO.getContent());
        // }
        // model.addAttribute("list", boardService.???????????????????????????(cri));
        model.addAttribute("pageMaker", new PageDto(cri, boardService.??????????????????????????????()));

        return "board/main";
    }

    // main -> detail
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        // board data (????????? ?????? username ??????)
        BoardDetailDto board = boardService.?????????????????????(id);
        model.addAttribute("board", board);

        // reply data
        List<Reply> replyList = replyService.????????????????????????();
        model.addAttribute("replyList", replyList);

        // love data
        User principal = new User();
        if (session.getAttribute("principal") != null) {
            principal = (User) session.getAttribute("principal");
        }

        LoveDto love = loveService.??????????????????????????????(id, principal.getId());
        model.addAttribute("love", love);

        return "board/detail";
    }

    @PostMapping("/board/insert")
    public String insert(BoardDto boardDto) {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("???????????? ???????????????", HttpStatus.UNAUTHORIZED);
        }

        if (boardDto.getTitle() == null || boardDto.getContent().isEmpty()) {
            throw new CustomException("username??? ??????????????????");
        }

        if (boardDto.getTitle() == null || boardDto.getContent().isEmpty()) {
            throw new CustomException("password??? ??????????????????");
        }

        boardService.???????????????(user.getId(), boardDto.getTitle(), boardDto.getContent());

        return "redirect:/";
    }

    @RequestMapping(value = "board/{id}/{userId}/delete", method = { RequestMethod.DELETE })
    @ResponseBody
    public String delete(@PathVariable int id, @PathVariable int userId) {
        /*
         * !! ???????????? ?????? : DB log??????
         */
        Gson gson = new Gson();

        // 1. ??????
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return gson.toJson(new ResponseDto<>(-1, "????????? ??????", false));
        }

        // 2. ????????????
        if (user.getId() != userId) {
            return gson.toJson(new ResponseDto<>(-1, "?????? ??????", false));
        }

        // 3. ????????? ?????? - Service
        int res = boardService.?????????????????????(id);
        if (res == -2) {
            return gson.toJson(new ResponseDto<>(-2, "?????? ??????", false));
        }
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-2, "DB??????", false));
        }

        return gson.toJson(new ResponseDto<>(1, "????????? ?????? ??????", true));
    }

    @RequestMapping(value = "board/{id}/{userId}/update", method = { RequestMethod.PUT })
    @ResponseBody
    public String update(@PathVariable int id, @PathVariable int userId, @RequestBody BoardDetailDto boardPut) {
        Gson gson = new Gson();
        // 1. ??????
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            return gson.toJson(new ResponseDto<>(-1, "????????? ??????", false));
        }

        // 2. ????????????
        if (((User) session.getAttribute("principal")).getId() != userId) {
            return gson.toJson(new ResponseDto<>(-1, "?????? ??????", false));
        }

        // 3. ????????? ?????? - Service
        int res = boardService.?????????????????????(boardPut);
        if (res != -3) {
            return gson.toJson(new ResponseDto<>(-2, "????????? ???????????? ??????", false));
        }
        if (res != -2) {
            return gson.toJson(new ResponseDto<>(-2, "?????? ??????", false));
        }
        if (res != 1) {
            return gson.toJson(new ResponseDto<>(-2, "DB ??????", false));
        }

        return gson.toJson(new ResponseDto<>(1, "????????? update ??????", true));

    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("???????????? ???????????????");
        }

        return "board/saveForm";
    }

    @GetMapping("/board/{id}/{boardUserId}/updateForm")
    public String updateForm(@PathVariable int id, @PathVariable int boardUserId, Model model) {
        // 1. ??????
        User user = (User) session.getAttribute("principal");
        if (user == null) {
            throw new CustomException("???????????? ???????????????");
        }

        // 2. ??????
        if (user.getId() != boardUserId) {
            throw new CustomException("?????? ????????? ????????????");
        }

        BoardDetailDto board = boardService.?????????????????????(id);

        model.addAttribute("board", board);
        return "board/updateForm";
    }

}
