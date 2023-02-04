package shop.mtcoding.blog.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyRepository {
    public int insert(@Param("boardId") int boardId, @Param("userId") int userId,
            @Param("username") String username, @Param("content") String content);

    public List<Reply> findByAll();

    public Reply findById(int id);

    public int updateById(@Param("id") int id, @Param("content") String content);

    public int deleteById(int id);
}