package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blog.dto.board.BoardDetailDto;
import shop.mtcoding.blog.dto.board.BoardRecentDto;

@Mapper
public interface BoardRepository {
        public int insert(@Param("userId") int userId, @Param("title") String title,
                        @Param("content") String content);

        public List<Board> findByAll();

        public BoardRecentDto findRecentInsert();

        public Board findById(int id);

        public BoardDetailDto findByIdForUsername(int id);

        public int updateById(@Param("id") int id, @Param("title") String title,
                        @Param("content") String content);

        public int deleteById(int id);
}
