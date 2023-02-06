package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.dto.board.BoardRecentDto;
import shop.mtcoding.blog.vo.BoardVO;

@Mapper
public interface BoardRepository {
        public int insert(@Param("userId") int userId, @Param("title") String title,
                        @Param("content") String content, @Param("loveCnt") int loveCnt);

        public List<Board> findByAll();

        public List<BoardVO> findListPaging(@Param("beginCheck") int beginCheck, @Param("endCheck") int endCheck);

        public List<Board> findSearchContent(String title);

        public int getTotalCount();

        public List<Board> findByAllOrederByLove();

        public BoardRecentDto findRecentInsert();

        public Board findById(int id);

        public BoardDetailDto findByIdForUsername(int id);

        public int updateById(@Param("id") int id, @Param("title") String title,
                        @Param("content") String content);

        public int updateCntById(@Param("id") int id, @Param("loveCnt") int loveCnt);

        public int deleteById(int id);
}
