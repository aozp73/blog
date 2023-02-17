package shop.mtcoding.blog.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.dto.love.LoveDto;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.Love;
import shop.mtcoding.blog.model.LoveRepository;

@RequiredArgsConstructor
@Service
public class LoveService {

    private final LoveRepository loverepository;
    private final BoardRepository boardrepository;

    public LoveDto 게시물좋아요상태체크(int boardId, int principalId) {
        Love love = loverepository.findByBoardIdAndActedUserId(boardId, principalId);
        if (love == null) {
            return new LoveDto(1, null);
        }

        if (love.isCheck() == true) {
            return new LoveDto(1, true);
        } else {
            return new LoveDto(0, false);
        }
    }

    public int 좋아요테이블체크(int boardId, int principalId) {
        Love love = loverepository.findByBoardIdAndActedUserId(boardId, principalId);
        Board board = boardrepository.findById(boardId);

        // 최초 좋아요한 적이 없다면 테이블 생성
        if (love == null) {
            int res = loverepository.insert(boardId, board.getUserId(), principalId);
            if (res != 1) {
                return -1;
            }
        }

        return 1;
    }

    public LoveDto 좋아요isCheck세팅(int boardId, int principalId) {
        Love love = loverepository.findByBoardIdAndActedUserId(boardId, principalId);
        boolean isCheck = love.isCheck();

        if (isCheck == false) {
            // 기존 false면 true로 update
            int res1 = loverepository.updateById(love.getId(), true);
            if (res1 != 1) {
                return new LoveDto(-1, false);
            }
            return new LoveDto(1, true);
        } else {
            // 기존 true면 false로 update
            int res2 = loverepository.updateById(love.getId(), false);
            if (res2 != 1) {
                return new LoveDto(-1, false);
            }
            return new LoveDto(1, false);
        }

    }

    // public List<Love> 좋아요전체리스트() {
    // List<Love> loveList = loverepository.findByAll();
    // return loveList;
    // }

}
