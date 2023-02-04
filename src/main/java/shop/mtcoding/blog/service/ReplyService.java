package shop.mtcoding.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.ReplyRepository;
import shop.mtcoding.blog.model.UserRepository;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    public int 댓글삭제하기(int boardId, int replyId, int boardUserId, int replyUserId) {
        // 댓글 존재 확인
        Reply reply = replyRepository.findById(replyId);
        if (reply == null) {
            return -1;
        }

        // 본인이 쓴 reply인지 확인
        if (replyUserId != reply.getUserId()) {
            return -1;
        }

        // 댓글 삭제
        int res = replyRepository.deleteById(replyId);
        if (res != 1) {
            return -2;
        }

        return 1;

    }

    public int 댓글쓰기(Reply reply) {
        // boardId, userId 유효성 검사
        if (boardRepository.findById(reply.getBoardId()) == null) {
            return -1;
        }

        if (userRepository.findById(reply.getUserId()) == null) {
            return -1;
        }

        // reply table (insert)
        int res = replyRepository.insert(reply.getBoardId(), reply.getUserId(),
                reply.getUsername(), reply.getContent());
        if (res != 1) {
            return -1;
        }

        return 1;
    }

    public List<Reply> 댓글목록불러오기() {
        List<Reply> replyList = replyRepository.findByAll();
        return replyList;
    }
}
