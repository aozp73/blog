package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoveRepository {
        public int insert(@Param("boardId") int boardId, @Param("boardUserId") int boardUserId,
                        @Param("actedUserId") int actedUserId);

        public List<Love> findByAll();

        public Love findByBoardIdAndActedUserId(@Param("boardId") int boardId,
                        @Param("actedUserId") int actedUserId);

        public Love findById(int id);

        public int updateById(@Param("id") int id, @Param("isCheck") boolean isCheck);

        public int deleteById(int id);
}
