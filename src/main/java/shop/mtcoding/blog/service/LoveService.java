package shop.mtcoding.blog.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.board.BoardRecentDto;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.Love;
import shop.mtcoding.blog.model.LoveRepository;

@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveRepository loverepository;
    private final BoardRepository boardrepository;

    public int 좋아요테이블생성() {
        BoardRecentDto board = boardrepository.findRecentInsert();
        int res = loverepository.insert(board.getId(), board.getUserId(), 0, false, 0);
        if (res != 1) {
            return -1;
        }
        return 1;
    }

    public int 좋아요상태체크(int boardId, int principalId) {
        Love love = loverepository.findByBoardIdAndActedUserId(boardId, principalId);
        if (love == null) {
            return -1;
        }

        return 1;
    }
}
