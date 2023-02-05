package shop.mtcoding.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.dto.love.LoveDto;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.vo.BoardVO;
import shop.mtcoding.blog.vo.Criteria;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final HttpSession session;

    public int 좋아요반영(int boardId, LoveDto loveDto) {
        Board board = boardRepository.findById(boardId);
        Boolean isCheck = loveDto.getIsCheck();
        if (isCheck == true) {
            int res1 = boardRepository.updateCntById(boardId, board.getLoveCnt() + 1);
            if (res1 != 1) {
                return -1;
            }

        } else {
            int res2 = boardRepository.updateCntById(boardId, board.getLoveCnt() - 1);
            if (res2 != 1) {
                return -1;
            }
        }
        return 1;
    }

    public int 게시글등록(int userId, String title, String content) {
        // board_tb 반영
        content = content.replaceAll("<p>", "");
        content = content.replaceAll("</p>", "");
        int res = boardRepository.insert(userId, title, content, 0);
        if (res != 1) {
            return -1;
        }
        return 1;
    }

    // public List<BoardVO> 페이징게시물리스트(Criteria cri) {
    // return boardRepository.findListPaging(cri);
    // }

    public int 페이징전체게시물갯수() {
        return boardRepository.getTotalCount();
    }

    public List<Board> 게시글불러오기() {
        List<Board> boardList = boardRepository.findByAllOrederByLove();
        return boardList;
    }

    public BoardDetailDto 게시글상세보기(int id) {
        // 작성한 username 저장
        BoardDetailDto board = boardRepository.findByIdForUsername(id);
        return board;
    }

    public int 게시글수정하기(BoardDetailDto modifiedBoard) {
        int res = boardRepository.updateById(modifiedBoard.getId(), modifiedBoard.getTitle(),
                modifiedBoard.getContent());
        if (res != 1) {
            return -1;
        }
        return 1;
    }

    public int 게시글삭제하기(int id) {
        // 게시글 존재여부 확인
        Board board = boardRepository.findById(id);
        if (board == null) {
            return -3;
        }

        // 본인이 쓴 board인지 확인
        User user = (User) session.getAttribute("principal");
        if (user.getId() != board.getUserId()) {
            return -1;
        }

        // 게시글 삭제
        int res = boardRepository.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 1;
    }
}
