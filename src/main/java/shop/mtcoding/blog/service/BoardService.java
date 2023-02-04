package shop.mtcoding.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public int 게시글등록(int userId, String title, String content) {
        // board_tb 반영
        content = content.replaceAll("<p>", "");
        content = content.replaceAll("</p>", "");
        int res = boardRepository.insert(userId, title, content);
        if (res != 1) {
            return -1;
        }
        return 1;
    }

    public List<Board> 게시글불러오기() {
        List<Board> boardList = boardRepository.findByAll();
        return boardList;
    }

    public BoardDetailDto 게시글상세보기(int id) {
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
}
